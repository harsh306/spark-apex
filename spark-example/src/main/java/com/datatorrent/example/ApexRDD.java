package com.datatorrent.example;

import com.datatorrent.api.DAG;
import com.datatorrent.api.DefaultInputPort;
import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.api.Operator;
import com.datatorrent.common.util.BaseOperator;
import com.datatorrent.stram.plan.logical.LogicalPlan;
import org.apache.spark.Partition;
import org.apache.spark.TaskContext;
import org.apache.spark.rdd.RDD;
import scala.Function1;
import scala.Function2;
import scala.collection.Iterator;
import scala.reflect.ClassTag;

public class ApexRDD<T> extends RDD<T>
{
  private static final long serialVersionUID = -3545979419189338756L;

  public DAG dag;
  public Operator currentOperator;
  public OperatorType currentOperatorType;

  public ApexRDD(RDD<T> rdd, ClassTag<T> classTag)
  {
    super(rdd, classTag);
  }

  public ApexRDD(ApexContext ac)
  {
      Integer
    super(ac.emptyRDD((ClassTag<T>) scala.reflect.ClassManifestFactory.fromClass(Object.class)), (ClassTag<T>) scala.reflect.ClassManifestFactory.fromClass(Object.class));
    dag = new LogicalPlan();
  }

  public DAG getDag()
  {
    return dag;
  }
    @Override
  public <U> RDD<U> map(Function1<T, U> f, ClassTag<U> evidence$3)
  {
    MapOperator m1 = dag.addOperator("Map"+System.currentTimeMillis(), MapOperator.class);
    m1.f = f;
    return (ApexRDD<U>)this;
  }

    @Override
    public RDD<T> filter(Function1<T, Object> f) {
        FilterOperator filterOperator = dag.addOperator("Filter"+System.currentTimeMillis(),FilterOperator.class);
        filterOperator.f=f;
        return this;
    }

    @Override
    public T reduce(Function2<T, T, T> f) {
        ReduceOperator reduceOperator = dag.addOperator("Reduce "+System.currentTimeMillis(),ReduceOperator.class);
        reduceOperator.f2=f;
        return (T) this;
    }

    @Override
  public Iterator<T> compute(Partition arg0, TaskContext arg1)
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Partition[] getPartitions()
  {
    // TODO Auto-generated method stub
    return null;
  }

  public enum OperatorType {
    INPUT,
    PROCESS,
    OUTPUT
  }
  public static class ReduceOperator extends BaseOperator{
      public Function2 f2;
      DefaultOutputPort outputPort = new DefaultOutputPort();
      DefaultInputPort  inputPort = new DefaultInputPort() {
          @Override
          public void process(Object tuple) {
              outputPort.emit(f2.apply(tuple,tuple));
          }
      };
  }
  public static class FilterOperator extends  BaseOperator{
      public Function1 f;
      public final transient DefaultInputPort inputPort = new DefaultInputPort() {
          @Override
          public void process(Object tuple) {
              f.apply(tuple);
          }
      };
      public final transient  DefaultOutputPort outputPort = new DefaultOutputPort();

  }
  public static class MapOperator extends BaseOperator
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
}
