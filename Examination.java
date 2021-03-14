/**
 * Class to define a patient in examination
 *
 */
public class Examination implements Comparable<Examination>{
	
	private double examinationTime;  
	private ExaminationRoom room; 
	
	public Examination(double examinatinoTime, ExaminationRoom room){
		 this.examinationTime = examinatinoTime;
		 this.room = room; 
	}
	
	public double getDischargeTime(){
		return examinationTime;
	}
	
	public ExaminationRoom getExamRoom(){
		return room; 
	}
	
	// returns the room number we are in 
	public int getRoomNum(){
		return room.getRoomNumber();
	}
	
	// provides comparison
	@Override
	public int compareTo(Examination o) {
		if(o.examinationTime < examinationTime){
			return 1;
		}
		return -1;
	}

}