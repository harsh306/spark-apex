package com.datatorrent.example;

import org.apache.spark.SparkConf;

public class ApexConf extends SparkConf
{
  public ApexConf()
  {
  }

  @Override
  public ApexConf setMaster(String master)
  {
    return (ApexConf) super.setMaster(master);
  }

  @Override
  public ApexConf setAppName(String name)
  {
    return (ApexConf) super.setAppName(name);
  }
}
