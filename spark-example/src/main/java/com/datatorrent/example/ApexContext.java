package com.datatorrent.example;

import org.apache.apex.malhar.lib.fs.LineByLineFileInputOperator;
import org.apache.spark.SparkContext;
import org.apache.spark.rdd.RDD;

public class ApexContext extends SparkContext
{
  public ApexContext()
  {
    super(new ApexConf());
  }

  public ApexContext(ApexConf config)
  {
    super(config);
  }

  @Override
  public RDD<String> textFile(String path, int minPartitions)
  {
    ApexRDD rdd = new ApexRDD<>(this);
    LineByLineFileInputOperator fileInput = rdd.getDag().addOperator("Input", LineByLineFileInputOperator.class);
    fileInput.setDirectory(path);
    fileInput.setPartitionCount(minPartitions);
    System.out.println(rdd.getDag().toString());
    rdd.currentOperator = fileInput;
    rdd.currentOperatorType = ApexRDD.OperatorType.INPUT;
    return rdd;
  }
}
