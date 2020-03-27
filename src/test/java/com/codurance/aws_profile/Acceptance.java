package com.codurance.aws_profile;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileNotFoundException;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class Acceptance {

  @Mock
  private Printer printer;

//  [profile toms_profile]
//  output = json
//  region = eu-west-1

  // repo fetch files from
  // file object migrate -
  @Test
  void lists_available_profiles() throws FileNotFoundException {
    String filePath = System.getProperty("user.dir") + "/src/test/java/com/codurance/aws_profile/aws-test" + "/credentials";

    AWSProfileSwitcher awsProfileSwitcher = new AWSProfileSwitcher(printer, filePath);

    awsProfileSwitcher.list();


    // 1. Test -> AWSProfileSwitcher
    // 2. Confused path awsFileReader - awsFileReader

    // 3. Need a means of getting profile within []

    verify(printer).printline("toms_profile");

  }
}
