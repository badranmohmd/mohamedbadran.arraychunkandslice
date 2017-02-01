package mohamedbadran.arraychunkandslice;

import org.apache.log4j.Logger;

import com.appiancorp.services.ServiceContext;
import com.appiancorp.suiteapi.expression.annotations.Function;
import com.appiancorp.suiteapi.expression.annotations.Parameter;

/**
 * 
 * @author Mohamed Badran - 12/28/2016 - mohammedbadran14@gmail.com
 */

@ArrayFunctionsCategory
public class ChunkArray {

	private static final Logger LOG = Logger.getLogger(ChunkArray.class);
	private static final String INPUT_NAME_CHUNKARRAY = "ChunkArray";
	private String[] outputArray = null;
	private String errorMsg;
	private Exception e;
	

	@Function
	public String[] chunk(ServiceContext sc, @Parameter String[] array, @Parameter Long size,
			@Parameter Boolean preserveKey, @Parameter String separator) {
		
		outputArray = ChunkArrayUtil(array, size.intValue(), preserveKey, separator);
		return outputArray;
	}
	
	
    /**
     * Definition and Usage:
     *  The function chunks an input array into arrays "chunks" with 
     *  size elements. The last chunk may contain less than size elements.
     * @param array: 
     *  Required. Specifies the array to use
     * @param size:
     *  Required. An integer that specifies the size of each chunk
     * @param preserveKey
     *  Required. Possible values:
     *                  True - Preserves the keys, False - Re-indexes chunks
     * @param separator
     *  Required. Specifies the separator text to use,  
     *  e.g. "|"(pipe)
     * @return
     *  The 1D representation of the 2D indexed array containing the chunks
     */
    String[] ChunkArrayUtil(String[] array, int size, Boolean preserveKey, 
            String separator)
    {
        
        if(array == null || array.length == 0)
        {
            errorMsg = "Array input should not be null or empty";
            LOG.error(String.format("Unexpected error trying to run "+ INPUT_NAME_CHUNKARRAY + ": " + errorMsg), e);
            return null;
        }
        
        if(size <= 0)
        {
            errorMsg = "Chunk size expected to be greater than zero";
            LOG.error(String.format("Unexpected error trying to run "+ INPUT_NAME_CHUNKARRAY + ": " + errorMsg), e);
            return null;
        }
        
		if (separator == null || separator.equals(""))
		{
            errorMsg = "Separator input should not be null or empty";
            LOG.error(String.format("Unexpected error trying to run "+ INPUT_NAME_CHUNKARRAY + ": " + errorMsg), e);
            return null;
		}
    	
		
        int rows;
        int columns;
        
        if(preserveKey == false)
        {
            rows = 0;
            if(array.length % size == 0)
            {
                rows = array.length/size;
            }
            else
            {
                rows = (array.length/size)+1;           
            }

            columns = size;
        }
        else
        {
            rows = 0;
            if(array.length % size == 0)
            {
                rows = array.length/size;
            }
            else
            {
                rows = (array.length/size)+1;           
            }
            
            columns = array.length;
        }
        
        String[][] chunks = new String[rows][columns];
        int index;
        int count;
        int jump;
        int key;
        
        index = 0;
        jump = 0;
        key = 0;
        count = 0;
        
        if(preserveKey == false)
        {
            for(index = 0; index < array.length; index++){
                
                chunks [(int)(index / size)][jump] = 
                        array[index];
                count++;
                if(count % columns == 0)
                    jump = 0;
                else
                    jump++;
            }
        }
        else
        {
            for(index = 0; index < array.length; index++){
                key = index;
                chunks [(int)(index / size)][key] = array[index];
            }
        }
        
        String[] flatArr = 
                new FlattenArray().FlattenArrayUtil(chunks, separator);
                
        return flatArr;
    }
}
