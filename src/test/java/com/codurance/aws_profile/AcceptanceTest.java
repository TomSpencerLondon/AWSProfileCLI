package com.codurance.aws_profile;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AcceptanceTest {

  @Mock
  private Printer printer;

  @Test
  void apps_list_shows_profiles() throws FileNotFoundException {
    String[] path = {"/src/test/",
            "java/com/codurance/",
            "aws_profile/aws-test",
            "/credentials"};
    String filePath = System.getProperty("user.dir") + String.join("", path);
    AWSProfileSwitcher awsProfileSwitcher = new AWSProfileSwitcher(printer, filePath);

    awsProfileSwitcher.execute("aps list");

    verify(printer).print("toms_profile\n" +
                          "harrys_profile\n" +
                          "jerrys_profile");
  }
}
