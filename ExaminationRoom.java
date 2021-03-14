//done
/**
 * A class to manage the order of the examination room according to their busy level.
 */
	 
public class ExaminationRoom implements Comparable<ExaminationRoom>{
	
	private int roomNumber; 
	private double timeUsed; // total time of the room in use
	
	public ExaminationRoom(int roomNumber, double timeUsed){
		this.roomNumber = roomNumber;
		this.timeUsed = timeUsed;
	}
	
	/**
	 * return room number
	 * @return
	 */
	public int getRoomNumber(){
		return roomNumber; 
	}
	
	/**
	 * return how long the room has been used
	 * @return
	 */
	public double getTimeUsed(){
		return timeUsed; 
	}

	/**
	 * set how long the room has been used
	 * @param newTotalTime
	 */
	public void setTimeUsed(double newTotalTime){
		this.timeUsed = newTotalTime; 
	}
	
	/**
	 * Compare the busy level of the examination room
	 */
	@Override
	public int compareTo(ExaminationRoom o) {
		// return 1 when the compared room is busier
		if(timeUsed < o.timeUsed){ 
			return 1; 
		}
		return -1; 
	}
	
	/**
	 * print out the using information of the examination room 
	 */
	public String toString(){
		return "Room Number: " + getRoomNumber() +  "\nThe room has been used for: " + getTimeUsed() + "mins.";
	}
	
	

}