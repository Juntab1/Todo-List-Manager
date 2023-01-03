// Program: TodoList Extension
// This class is just like the todo list program from before but now for this todo list you can 
// add due dates to the items you add.

import java.util.*;
import java.io.*;

public class TodoListManager {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        List<String> toDo = new ArrayList<>();
        List<String> dateList = new ArrayList<>();
        System.out.println("Welcome to your TODO List Manager!");
        System.out.println("What would you like to do?");
        System.out.print("(A)dd TODO, (M)ark TODO as done, (L)oad TODOs, (S)ave TODOs, (Q)uit? ");
        String userSelect = console.nextLine();
        while (!userSelect.equalsIgnoreCase("q")){
            if (userSelect.equalsIgnoreCase("a")){
                addAndDueDate(console, toDo, dateList);
            }   
            else if (userSelect.equalsIgnoreCase("m")){
                markItemAsDone(console, toDo, dateList);
            }
            else if (userSelect.equalsIgnoreCase("l")){
                loadItems(console, toDo);
            }
            else if (userSelect.equalsIgnoreCase("s")){
                saveItems(console, toDo);
            }
            else if (!userSelect.equalsIgnoreCase("q")){
                System.out.println("Unknown input: " + userSelect);
            }
            printTodos(toDo,dateList);
            System.out.println("What would you like to do?");
            System.out.print("(A)dd TODO, (M)ark TODO as done, (L)oad TODOs, (S)ave TODOs, (Q)uit? ");
            userSelect = console.nextLine();
        }
    }
    
    // This method prints out the user the items they have on their todo lists onto the console
    // and also the deadline with those items.
    // parameter:
    //      - todos: arraylist used to store the items of the user's todo list
    //      - dateList: arraylist used to store the deadline of the items of the user's todo list
    public static void printTodos(List<String> todos, List<String> dateList) {
        System.out.println("Today's TODOs:");
        if (!todos.isEmpty()){
            for (int i = 0; i < todos.size(); i++) {
			    System.out.println("  " + (i+1) + ": " + todos.get(i));
                System.out.println("    Deadline: " + dateList.get(i));
		    }
        }
        else{
            System.out.println("  You have nothing to do yet today! Relax!");
        }
    }

    // This method lets the user add items to their todo list, and 
    // lets the user choose where in the list they would like to put it. 
    // parameter:
    //      - console: scanner lets user type in the console box so we can get user input
    //      - todos: arraylist used to store the items of the user's todo list
    // return: the number of items in the todos arraylist and also the number of where the user
    // wants to put the item
    public static int addItem(Scanner console, List<String> todos) {
        System.out.print("What would you like to add? ");
        String addItem = console.nextLine();
        if (!todos.isEmpty()){
            System.out.print("Where in the list should it be " + "(1-" + (todos.size()+1) + ")? (Enter for end): ");
            String addPosition = console.nextLine();
            if (addPosition.isEmpty()){
                todos.add(addItem);
                return todos.size();
            }
            else{
                int numAddPosition = Integer.parseInt(addPosition);
                todos.add(numAddPosition-1, addItem);
                return numAddPosition;
            }
        }
        else{
            todos.add(addItem);
            return todos.size();
        }
    }

    // This method lets the user remove items they have on their arraylist toDo.
    // parameter:
    //      - console: scanner lets user type in the console box so we can get user input
    //      - todos: arraylist used to store the items of the user's todo list
    //      - dateList: arraylist used to store the deadline of the items of the user's todo list
    public static void markItemAsDone(Scanner console, List<String> todos, List<String> dateList) {
        System.out.print("Which item did you complete " + "(1-" + todos.size() + ")? ");
        String removeNum = console.nextLine();
        int numOfRemove = Integer.parseInt(removeNum);
        if (!todos.isEmpty()){
            todos.remove(numOfRemove-1);
            dateList.remove(numOfRemove-1);
        }
        else{
            System.out.println("All done! Nothing left to mark as done!");
        }
    }

    // This method puts the list of items the user has to do
    // from the file they give us into the arraylist(toDo) we created in main.
    // parameter:
    //      - console: scanner lets user type in the console box so we can get user input
    //      - todos: arraylist used to store the items of the user's todo list
    // the FileNotFoundException is for when the user inputs a file name that can't be found
    public static void loadItems(Scanner console, List<String> todos) throws FileNotFoundException {
        System.out.print("File name? ");
        String fileLoad = console.nextLine();
        Scanner loadScan = new Scanner(new File(fileLoad));
        while (loadScan.hasNextLine()){
            todos.add(loadScan.nextLine());
        }
    }

    // This method lets the user create a new file and save
    // the items they currently have on their arraylist toDo
    // to the new file they create.
    // parameter:
    //      - console: scanner lets user type in the console box so we can get user input
    //      - todos: arraylist used to store the items of the user's todo list
    // the FileNotFoundException is for when the user inputs a file name that can't be found
    public static void saveItems(Scanner console, List<String> todos) throws FileNotFoundException {
        System.out.print("File name? ");
        String fileSave = console.nextLine();
        PrintStream output = new PrintStream(new File(fileSave));
        for (int i = 0; i < todos.size(); i++) {
            output.println(todos.get(i));
        }
    }

    // This method lets the user add a deadline with each item they add.
    // parameter:
    //      - console: scanner lets user type in the console box so we can get user input
    //      - todos: arraylist used to store the items of the user's todo list
    //      - dateList: arraylist used to store the date of each item on the user's todo list
    public static void addAndDueDate(Scanner console, List<String> todos, List<String> dateList) {
        int numAddPosition = addItem(console, todos);
        System.out.print("What is the deadline for this assignment? ");
        String dueDate = console.nextLine();
        dateList.add(numAddPosition-1, dueDate);
    }

}