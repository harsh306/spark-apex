package com.datatorrent.example;

import org.apache.hadoop.mapred.InputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.spark.SparkContext;
import org.apache.spark.rdd.RDD;

import com.datatorrent.api.DAG;

import scala.Tuple2;

public class ApexContext extends SparkContext
{

  DAG dag;

  @Override
  public RDD<String> textFile(String path, int minPartitions)
  {
    return super.textFile(path, minPartitions);
  }

  @Override
  public <K, V> RDD<Tuple2<K, V>> hadoopFile(String path, Class<? extends InputFormat<K, V>> inputFormatClass,
      Class<K> keyClass, Class<V> valueClass, int minPartitions)
  {
//    FileInputFormat.setInputPaths(job, inputPaths);
    return super.hadoopFile(path, inputFormatClass, keyClass, valueClass, minPartitions);
  }
}
