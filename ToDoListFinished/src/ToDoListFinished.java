// ===============================================
// A simple program for crafting a to-do list that will sort
// by the deadlines.
// C.Brock
// ==============================================

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Stream;

public class ToDoListFinished
{
	class Deadline
	{  
		int deadline;   
		Deadline(int deadline)  
		{  
			this.deadline = deadline;  
		}
	}  

	class Taskname
	{  
		String taskName;   
		Taskname(String taskName)  
		{  
			this.taskName = taskName;  
		}
	}  

	public static void main(String[] args)
	{
		String taskName;
		int deadline = 0;
		int option = -1;

		// Creates the unsorted list
		TreeMap <String, Integer> taskList = new TreeMap<>();

		// Main menu that the code will continue to circle back to
		do 
		{
			// Main menu
			System.out.println("\nTO DO:");
			System.out.println("Select an option:");
			System.out.println("-----------------------");
			System.out.println("1: Review list");
			System.out.println("2: Add a task");
			System.out.println("3: Remove a task");
			System.out.println("4: Exit menu");
			System.out.println("-----------------------");

			try 	
			{
				Scanner scan = new Scanner(System.in);
				option = scan.nextInt();

				switch(option) {
				case 1:

					Map<String, Integer> sortedList = new LinkedHashMap<>();
					taskList.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue()).forEachOrdered(x -> sortedList.put(x.getKey(), x.getValue()));

					// Goes through the list to print a version sorted by the soonest deadline.
					for(Map.Entry<String,Integer>it:sortedList.entrySet())
						System.out.println(it.getKey() + " is due in " + it.getValue()+" days. ");

					break;

				case 2:
					// Creates the task
					String currentTask = "null";
					int currentDeadline = 0;

					System.out.print("Enter the name of the task:");
					System.out.println();
					scan.nextLine();
					currentTask = scan.nextLine();
					System.out.println("\nEnter how many days until the deadline:");
					currentDeadline = scan.nextInt();

					// Adds it to the tree map
					taskList.put(currentTask, currentDeadline);
					break;

				case 3:
					// Removes a task
					System.out.println("Name of the task to be removed:");
					scan.nextLine();
					for(Map.Entry<String,Integer>it:taskList.entrySet())
						System.out.println(it.getKey());
					String removeTask = scan.nextLine();
					taskList.remove(removeTask);
					break;

				case 4:
				{
					//Terminates the program
					System.exit(0);
				}

				default:
					throw new IllegalArgumentException();
				}
			}

			catch (InputMismatchException ex) 
			{
				System.out.println("ERROR: You must enter a number between 1-4");		
				System.out.println("Press 1 to return to the menu.\r\n");		

				Scanner scan = new Scanner(System.in);
				option = scan.nextInt();
			}

			catch (IllegalArgumentException ex)
			{
				System.out.println("ERROR: You must enter a number between 1-4");		
				System.out.println("Press 1 to return to the menu.\r\n");	
			}
		} 		
		while (option > 0);
	}
}