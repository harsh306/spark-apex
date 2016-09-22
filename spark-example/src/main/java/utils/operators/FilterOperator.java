package utils.operators;

import com.datatorrent.api.DefaultInputPort;
import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.common.util.BaseOperator;
import scala.Function1;

/**
 * Created by harsh on 5/10/16.
 */
public class FilterOperator extends BaseOperator {
    public Function1 f;
    public final transient DefaultInputPort input = new DefaultInputPort() {
        @Override
        public void process(Object tuple) {
            if((Boolean)f.apply(tuple)){
                output.emit(tuple);
            }
        }
    };
    public static transient DefaultOutputPort output= new DefaultOutputPort();
}
