package com.codurance.aws_profile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class AWSProfileSwitcher {
  private final Printer printer;
  private final String pathConfig;
  private Map<String, String> env;


  public AWSProfileSwitcher(Printer printer, String pathConfig) {
    this.printer = printer;
    this.pathConfig = pathConfig;
  }

  public Map<String, String> getEnv() {
    return env;
  }

  public void execute(String command) throws IOException {
    if (command.equals("aps list")) {
      list();
    }
    if (command.contains("switch")){
      String[] switchCommands = command.split(" ");
      String profile = switchCommands[2];
      switchProfile(profile);
    }
  }

  public void switchProfile(String profile) {
    ProcessBuilder pb = new ProcessBuilder();
    env = pb.environment();
    env.put("AWS_PROFILE", profile);
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
