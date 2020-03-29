package com.codurance.aws_profile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AcceptanceTest {

  @Mock
  private Printer printer;
  private String[] path;
  private AWSProfileSwitcher awsProfileSwitcher;

  @BeforeEach
  void setUp() {
    path = new String[]{"/src/test/",
            "java/com/codurance/",
            "aws_profile/aws-test",
            "/credentials"};
    String filePath = System.getProperty("user.dir") + String.join("", path);
    awsProfileSwitcher = new AWSProfileSwitcher(printer, filePath);
  }

  @Test
  void apps_list_shows_profiles() throws IOException {
    awsProfileSwitcher.execute("aps list");
    verify(printer).print("toms_profile\n" +
                          "harrys_profile\n" +
                          "jerrys_profile");
  }

  @Test
  void aps_switch_changes_specified_profile_environment_variable() throws IOException {
    AWSProfileSwitcher awsProfileSwitcher = new AWSProfileSwitcher(printer, "path");
    awsProfileSwitcher.execute("aps switch toms_profile");

    assertEquals("toms_profile", awsProfileSwitcher.getEnv().get("AWS_PROFILE"));
    awsProfileSwitcher.execute("aps switch harrys_profile");
    assertEquals("harrys_profile", awsProfileSwitcher.getEnv().get("AWS_PROFILE"));
  }

  @Test
  void aps_add_access_key_secret_access_key_adds_a_new_profile() throws IOException {
    awsProfileSwitcher.execute("aps add johns_profile access_key secret_access_key");

  }
}
