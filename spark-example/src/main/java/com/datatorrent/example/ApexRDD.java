package com.datatorrent.example;

import org.apache.spark.Partition;
import org.apache.spark.SparkContext;
import org.apache.spark.TaskContext;
import org.apache.spark.rdd.RDD;

import com.datatorrent.api.DAG;
import com.datatorrent.api.Operator;
import com.datatorrent.stram.plan.logical.LogicalPlan;

import scala.Function1;
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
    return null;
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
