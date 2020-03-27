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

  public void list() throws FileNotFoundException {
    StringBuilder profile = new StringBuilder();

    File file = new File(pathConfig);

    Scanner reader = new Scanner(file);

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
