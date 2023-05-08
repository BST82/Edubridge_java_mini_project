package com.atm.machine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

 class Employee {

    private int empId;
    private String empName;

    public Employee(int empId, String empName) {
        super();
        this.empId = empId;
        this.empName = empName;
    }

    /**
     * @return the empId
     */
    public final int getEmpId() {
        return empId;
    }

    /**
     * @param empId
     *            the empId to set
     */
    public final void setEmpId(int empId) {
        this.empId = empId;
    }

    /**
     * @return the empName
     */
    public final String getEmpName() {
        return empName;
    }

    /**
     * @param empName
     *            the empName to set
     */
    public final void setEmpName(String empName) {
        this.empName = empName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Employee [empId=" + empId + ", empName=" + empName + "]";
    }

//    @Override
//    public int hashCode() {
//        return this.empId;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        Employee employee = (Employee) obj;
//        if (employee.empId == this.empId) {
//            employee.setEmpName(this.empName);
//            return true;
//        } else {
//            return false;
//        }
    
}
public class Main2Not_Related_With_Atm_project {

	public static void main(String[] args) {
		 
		String[] cityname= {"pune","noida","kanpur","chennai"};
		
//		ArrayList<String>citylist=
//				new ArrayList<String>(Arrays.asList(cityname));
//		Iterator<String> it = citylist.iterator();
//		
//		while(it.hasNext()) {
////			System.out.println(it.next());
//		}
		ArrayList list= new ArrayList<String>();
//		Collections.addAll(list,  cityname);
//		
//		for(String s:list) {
//			System.out.println(s);
//		}
		
//		for(int i=0;i<cityname.length;i++)
//		{
//			list.add(cityname[i]);
//		}
//	
//		for(int i=0;i<list.length();i++)
//		{
//			list.add(cityname[i]);
//		}
		
//		 Set<String> hash_Set = new HashSet<String>();
//		  
//	        // Adding elements to the Set
//	        // using add() method
//	        hash_Set.add("Geeks");
//	        hash_Set.add("For");
//	        hash_Set.add("Geeks");
//	        hash_Set.add("Example");
//	        hash_Set.add("Set");
//	  
//	        // Printing elements of HashSet object
//	        System.out.println(hash_Set);
		
	    Set<Employee> employees = new HashSet<>();
        employees.add(new Employee(1, "Raj"));
        employees.add(new Employee(1, "Raj"));
        employees.add(new Employee(1, "Raj"));
        employees.add(new Employee(1, "Raj"));
        employees.add(new Employee(1, "Raj"));
        employees.add(new Employee(2, "Chandan"));
        employees.add(new Employee(2, "Amitava"));

        System.out.println(employees);
	
	}
}

