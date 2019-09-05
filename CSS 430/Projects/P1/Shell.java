import java.util.ArrayList;
public class Shell extends Thread {

    /**
     * Code Shell.java, a Java thread that will be invoked from ThreadOS Loader
     * and behave as a shell command interpreter.
     */
    public Shell() {
    }

    public void run() {
        int cmArguments = 0; //command counter

        //Loops for additonal commands
        while (true) {
            cmArguments++;
            
            //output and input
            SysLib.cout("Shell[" + cmArguments + "]% ");
            StringBuffer commandB = new StringBuffer();
            SysLib.cin(commandB);
            String command = new String(commandB);
            ArrayList<Integer> processList = new ArrayList<>();

            //Executes command that is not blank
            if(command.equals("quit")){
                break;
            }
            
            if (!command.isEmpty()) {
                for (int i = 0; i < command.length(); i++) {
                    if (command.charAt(i) == '&') {
                        //excute command
                        processList.add(SysLib.exec(SysLib.stringToArgs(
                                command.substring(0, i - 1))));
                        //reduces the command and starts at 0 again
                        command = command.substring(i + 2);
                        i = -1;
                    } else if (command.charAt(i) == ';') {
                        //excute command
                        processList.add(SysLib.exec(SysLib.stringToArgs(
                                command.substring(0, i - 1))));
                        //reduces the command and starts at 0 again
                        command = command.substring(i + 2);
                        i = -1;
                        //wait for this child thread to be terminated
                        while (!processList.isEmpty()) {
                            int recentJoin = SysLib.join();
                            processList.remove(Integer.valueOf(recentJoin));
                        }
                    }
                }

            }
            //execute remaining string as a command
            if (!command.isEmpty()) {
                processList.add(SysLib.exec(SysLib.stringToArgs(command)));
            }
            while (!processList.isEmpty()) {
                int recentJoin = SysLib.join();
                processList.remove(Integer.valueOf(recentJoin));
            }
        }
        SysLib.exit();
    }
}
