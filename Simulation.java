import java.util.Scanner;

/**
 * Main class to run the simulation.
 */
public class Simulation {
	
	private Scanner scanner = new Scanner(System.in);

	private PriorityQueue<PatientVisit> patientQueue = new PriorityQueue<PatientVisit>();
	private PriorityQueue<Examination> examinationQueue = new PriorityQueue<Examination>();
	private PriorityQueue<ExaminationRoom> roomQueue = new PriorityQueue<ExaminationRoom>();

	private int roomNumber = 0;

	private boolean timeOver;

	public static void main(String[] args) {
		start();
	}

	
	/**
	 * method to run the simulation
	 */
	public static void start() {
		Simulation simulation = new Simulation();

		// check if there's a empty room for examination
		boolean examinationAvailable = false;

		// the current time, in hours
		double currentTime = 0;

		// user input for the number of examination rooms
		System.out.println("How many rooms will be used? ");
		int totalNumberOfRooms = simulation.scanner.nextInt() - 1; // index starts at 0 	
		int currentNumberOfRooms = totalNumberOfRooms;	
		int numPatients = 0; 

		// user input for simulation type
		System.out.println("Are patients generated randomly? (Yes or No)");
		String simulationType = simulation.scanner.next();

		// total time of room has been in use is 0
		double totalTime = 0;

		// populates the room queue with empty rooms
		while (currentNumberOfRooms >= 0) {
			simulation.roomNumber++;
			ExaminationRoom availableRoom = new ExaminationRoom(simulation.roomNumber, 0);
			simulation.roomQueue.insert(availableRoom);
			examinationAvailable = true;
			currentNumberOfRooms--;
		}

		// start the simulation of 10 hours 
		while (currentTime <= 60) {
			PatientVisit pv;
			// random patient visit
			if (simulationType.toLowerCase().startsWith("y")) {
				pv = PatientVisitGenerator.getNextRandomArrival(currentTime);
				numPatients ++;
			} 
			// programmed patient visit
			else {
				pv = PatientVisitGenerator.getNextProgrammedArrival(currentTime, totalNumberOfRooms);
				numPatients ++; 
			}
			currentTime++;

			// insert the patientVisit into the queue
			simulation.patientQueue.insert(pv);
			System.out.println("\n--------------------------------\n");
			int hr = (int)currentTime/60;
			int min = (int)currentTime%60;
			System.out.println("Current Time: " + hr + ":" + min + "\n");
			System.out.println("Number of waiting patients: " + simulation.patientQueue.getSize());
			
			if (examinationAvailable && !simulation.roomQueue.isEmpty()) {
				PatientVisit currentPatient = simulation.patientQueue.getFirst();
				// calculate the discharge time.
				double dischargeTime = currentPatient.getArrivalTime() + currentPatient.getDuration();

				// get room at the front of the queue
				// presumably the least visited room
				ExaminationRoom currentRoom = simulation.roomQueue.getFirst();
				totalTime = currentRoom.getTimeUsed() + currentPatient.getDuration();
				currentRoom.setTimeUsed(totalTime);

				// put patient in the least visited room at the front of the
				// room queue
				Examination patientExamined = new Examination(dischargeTime,
						simulation.roomQueue.getFirst());

				// dequeue the patient from patientQueue and
				// put them in the examinationQueue and remove an empty room
				// from the room Queue
				simulation.patientQueue.remove();
				simulation.roomQueue.remove(); // removes from the front
				simulation.examinationQueue.insert(patientExamined);
				System.out.println(
						"There are currently " + simulation.roomQueue.getSize() + " available rooms in the system.");
				System.out.println(
						"There are currently " + simulation.examinationQueue.getSize() + " patients being examined");
			}
			// discharged patient means empty room
			// triggers patient discharge
			if (simulation.examinationQueue.getFirst().getDischargeTime() >= currentTime) {
				ExaminationRoom availableRoom = simulation.examinationQueue.getFirst().getExamRoom();
				simulation.roomQueue.insert(availableRoom);
				simulation.examinationQueue.remove();
				System.out.println("A patient has been discharged.\nThere are currently "
						+ simulation.roomQueue.getSize() + " available rooms in the system.");
				System.out.println(
						"There are currently " + simulation.examinationQueue.getSize() + " patients being examined");
				examinationAvailable = true; // trigger available room flag
			}
		}
		
		System.out.println("\n------------------------\n");
		System.out.println("Simulation Result:\n");
		System.out.println("Total number of patients being treated: " + numPatients);
	}
	

}
