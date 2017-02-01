package mohamedbadran.arraychunkandslice;

/**
 * 
 * @author Mohamed Badran - 12/28/2016 - mohammedbadran14@gmail.com
 */
	class FlattenArray {


    /**
     * Definition and Usage: Converts a 2D array into 1D array representation by
     *  adding a separator at the end of each row.
     * @param table: 2D array to convert into 1D array
     * @param separator: Separates rows content after conversion.
     * @return: The 1D representation of 2D input array
     */
    String[] FlattenArrayUtil(String[][] table, String separator)
    {
        String flatArray[] = null;
        
        if(table == null || table.length == 0)
        {
            return null;
        }
        else
        {
            String[][] tempTable = new String [table.length][table[0].length + 1];
        
            /* copy table to temp table */
            for(int i=0; i<table.length; i++)
            {
                System.arraycopy(table[i], 0, tempTable[i], 0, table[i].length);
            /* insert the separator at the end of each row */
                tempTable[i][table[0].length]=separator;
            }
            
            
            flatArray = new String[tempTable.length * tempTable[0].length];
            
            for(int i = 0; i < tempTable.length; i++) {
                String[] row = tempTable[i];
                for(int j = 0; j < row.length; j++) {
                    String str = tempTable[i][j];
                    flatArray[i*row.length+j] = str;
                }
            }
            return flatArray;
        }  
    }
}
