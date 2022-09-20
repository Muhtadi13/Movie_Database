package util;

import java.io.Serializable;

public class InfoUtil implements Serializable {

  String instruction;
  Object info;

  public String getInstruction() {
    return instruction;
  }

  public void setInstruction(String instruction) {
    this.instruction = instruction;
  }

  public Object getInfo() {
    return info;
  }

  public void setInfo(Object info) {
    this.info = info;
  }

  public InfoUtil(String instruction, Object info) {

    this.instruction = instruction;
    this.info = info;

  }

}
