/**
 * CSS 143 B, Winter 2018 Recursion
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class Driver {

    /**
     * Testing Driver for Find File
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //instance of find file that finds a max of 3 files
        FindFile fileTest1 = new FindFile(3);
        int k = 0;
        try {
            try {
                //search file name and location
                fileTest1.directorySearch("FileTest.txt",
                        "/Users/margaretconnor/Desktop/javatest");
            } catch (Exception e) {
                //Max file exception catch
                System.out.println(e);
            } finally {
                //prints the results that were found 
                String[] newTest = fileTest1.getFiles();
                k = newTest.length;
                for (int i = 0; i < newTest.length; i++) {
                    System.out.println(newTest[i]);
                }
            }
        } catch (ItemNotFoundException e) {
            //file not found catch
            System.out.println(e);
        }
    }
}
