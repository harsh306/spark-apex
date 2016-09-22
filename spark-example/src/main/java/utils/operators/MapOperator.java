package utils.operators;

import com.datatorrent.api.DefaultInputPort;
import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.common.util.BaseOperator;
import scala.Function1;

/**
 * Created by harsh on 5/10/16.
 */
public class MapOperator extends BaseOperator
{
    public Function1 f;
    public final transient DefaultInputPort input = new DefaultInputPort() {
        @Override
        public void process(Object tuple) {
            output.emit(f.apply(tuple));
        }
    };
    public final transient DefaultOutputPort output = new DefaultOutputPort();
}

