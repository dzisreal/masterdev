package Bai3;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Salary implements Writable {
    public IntWritable salary = new IntWritable();

    public Salary(){}

    public Salary(int salary) {
        this.salary.set(salary);
    }
    @Override
    public void write(DataOutput out) throws IOException {
        this.salary.write(out);
    }
    @Override
    public void readFields(DataInput in) throws IOException {
        this.salary.readFields(in);
    }
}
