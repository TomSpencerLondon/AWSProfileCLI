# AWSProfileCLI

Spec- AWS Profile CLI Tool

Some devs have a number of AWS credentials they use. Luckily, AWS provides the "profiles" feature that enables one to store a number of credentials and switch between them. 

Specifically, these details are stored in two files- ~/.aws/config and ~/.aws/credentials. 


The format of config is like so (where profile_name is a placeholder for the name of the profile):
```
[profile profile_name]
output = json
region = eu-west-1
```
And the format of credentials is like so:
```
[profile_name]
aws_access_key_id = access_key
aws_secret_access_key = secret_access_key
```

A few notes:
*  The [ and ] characters are a part of the file format that identifies the name of the profile. Strangely, config uses [profile profile_name] while credentials uses just [profile_name]. This is just AWS being daft.

* The output is almost always going to be json unless the reader is from the year 3000, using some fourth-dimensional quantum markup language not yet invented.

* The region is often the same too. The credentials are much more likely to change.

* The spacing before and after the = is, AFAIK, required. The end of the value is denoted by end of line or end of file.

One can change one's AWS profile by setting the value of the environmental variable AWS_PROFILE. 

While AWS_PROFILE is set, all subsequent AWS CLI calls will use that profile for its configuration and authentication.

However, the interface for changing AWS profile is awkward.

If you don't recall what profiles you have available you need to read your configuration files, switching them requires setting an environment variable, and it isn't easy to add them either. 

Thus, we're going to write a simple CLI tool called aps (AWS Profile Switcher) with the following commands:

* aps list - lists available AWS profiles (newline separated).

* aps switch <profile> - switches to the specified profile (sets AWS_PROFILE)

* aps add <profile> <access_key> <secret_access_key> <region> <output> - adds a new profile (edited)