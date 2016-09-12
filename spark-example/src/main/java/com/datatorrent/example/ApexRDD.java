package com.datatorrent.example;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.rdd.RDD;

import scala.reflect.ClassTag;

public class ApexRDD<T> extends JavaRDD<T>
{
  public ApexRDD(RDD<T> rdd, ClassTag<T> classTag)
  {
    super(rdd, classTag);
  }

  @Override
  public <K2, V2> JavaPairRDD<K2, V2> mapToPair(PairFunction<T, K2, V2> f)
  {
    System.out.println("Inside MapToPair of ApexRDD" + f);
    return super.mapToPair(f);
  }
}
