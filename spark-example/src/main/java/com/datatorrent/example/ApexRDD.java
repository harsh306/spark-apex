package com.datatorrent.example;

import org.apache.spark.Partition;
import org.apache.spark.TaskContext;
import org.apache.spark.rdd.RDD;

import com.datatorrent.api.DAG;
import com.datatorrent.api.Operator;
import com.datatorrent.stram.plan.logical.LogicalPlan;


import scala.Function1;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import utils.operators.FilterOperator;
import utils.operators.MapOperator;

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
    MapOperator m1 = dag.addOperator("Map", MapOperator.class);
    m1.f = f;
    return (ApexRDD<U>)this;
  }

  @Override
  public RDD<T> filter(Function1<T, Object> f) {

    // Here I will have to write a filter operator as another entity on dag right?
    // but as the primary purpose of MapOperator above was apply() on every tuple...what for filter?
    FilterOperator f1 = dag.addOperator("Filter",FilterOperator.class);
    f1.f=f;
    return this;
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

}
