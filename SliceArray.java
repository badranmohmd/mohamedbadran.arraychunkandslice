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
public class SliceArray {
	private static final Logger LOG = Logger.getLogger(SliceArray.class);
	private static final String INPUT_NAME_SLICEARRAY = "SliceArray";
	private String[] outputArray = null;
	private String errorMsg;
	private Exception e;
	

	@Function
	public String[] slice(ServiceContext sc, @Parameter String[] array, @Parameter Long offset, @Parameter Long length,
			@Parameter Boolean preserveKey) {
		
		outputArray = SliceArrayUtil(array, offset.intValue(), length.intValue(), preserveKey);
		return outputArray;

	}

	
    /**
     * 
     * Definition and Usage: 
     *  The function returns the sequence of elements from the input array 
     *  as specified by the offset and length parameters.
     * @param array:
     *  Required. Specifies an array
     * @param offset:
     *  Required. Numeric value. Specifies where the function will start 
     *  the slice. 0 = the first element. If this value is set to a negative
     *  number, the function will start slicing that far from the last 
     *  element. -2 means start at the second last element of the array.
     * @param length:
     *  Required. Numeric value. Specifies the length of the returned array. 
     *  If this value is set to a negative number, the function will stop 
     *  slicing that far from the last element. If this value is not set, 
     *  the function will return all elements, starting from the position 
     *  set by the offset-parameter. 
     * @param preserveKey:
     *  Required. Specifies if the function should preserve or reset the 
     *  keys. Possible values:
                    true -  Preserve keys; false - Reset keys
     * @return: Returns selected parts of an array.
     */
    String[] SliceArrayUtil(String[] array, int offset, int length, 
            Boolean preserveKey)     
    {
        String[] temparr = null;
        int startIndex = 0;
        int elementsToCopy = 0;
        int adjustedArraySize = 0;
        
        if(array == null || array.length == 0)
        {
            errorMsg = "Array input should not be null or empty";
            LOG.error(String.format("Unexpected error trying to run "+ INPUT_NAME_SLICEARRAY + ": " + errorMsg), e);
            return null;
        }
        
        /* Setting the start index value */
        if(offset >= 0)
        {
            if(offset >= array.length)
            {
                return null;
            }
            else
            {
                startIndex = offset;
            }
        }
        else
        {
            if(offset <= (array.length * -1))
            {
                startIndex = 0;
            }
            else
            {
                startIndex = array.length + offset;
            }
        }

        /* Setting the number of elements to copy */
        if(length == 0)
        {
            return null;
        }
        else if(length > 0)
        {
            if(length + startIndex > array.length)
            {
                elementsToCopy = array.length - startIndex;
            }
            else
            {
                elementsToCopy = length;
            }
        }
        else
        {
            adjustedArraySize = array.length + length;
            
            if(adjustedArraySize <= startIndex)
            {
                return null;
            }
            else
            {
                elementsToCopy = adjustedArraySize - startIndex;
            }
        }
        
        if(preserveKey == false)
        {   
	        temparr = new String[elementsToCopy];
	        System.arraycopy(array, startIndex, temparr, 0, elementsToCopy);
        }
        else
        {     
	        temparr = new String[array.length];
	        System.arraycopy(array, startIndex, temparr, startIndex, 
	                elementsToCopy);
        
        }

        return temparr;
    }

}
