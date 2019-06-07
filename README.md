# training-search
just a small practice for selenium and java

# How to use as a library?
Just create a .jar file, and import it as dependency in your project. 

# Usage exapmle

// Just define the training name
Google google_properties = new Google("robot framework");

//Search will print out the results and it will create a csv file.
google_properties.searchTrainings(10);
