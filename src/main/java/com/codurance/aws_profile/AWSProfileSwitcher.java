package com.codurance.aws_profile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AWSProfileSwitcher {
  private final Printer printer;
  private final String pathConfig;


  public AWSProfileSwitcher(Printer printer, String pathConfig) {
    this.printer = printer;
    this.pathConfig = pathConfig;
  }

  public void execute(String command) throws FileNotFoundException {
    if (command.equals("aps list")) {
      list();
    }
  }

  public void list() throws FileNotFoundException {
    StringBuilder profile = new StringBuilder();
    Scanner reader = new Scanner(new File(pathConfig));

    while(reader.hasNextLine()){
      String line = reader.nextLine();
      if (line.charAt(0) == '['){
        profile.append(line.substring(1,line.length() - 1));
        profile.append("\n");
      }
    }

    String result = profile.toString().trim();
    printer.print(result);
  }
}
