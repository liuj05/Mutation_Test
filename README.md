# PITEST Mutation_Test

To Install PITEST Mutation Testing:

1. Clone the repository. 

2. Enter the directory of "pitest-parent-1.2.4" floder from comand line. 

3. Use command "mvn clean install -DskipTests=true".

To Apply PITEST Mutation Testing on Projects (Maven Projects):

1. Open pom.xml file, and add plugin as following:

  ```xml
  <plugin>
      	<groupId>org.pitest</groupId>
      	<artifactId>pitest-maven</artifactId>
      	<version>1.2.4</version>
      	<configuration>
        	<mutators>
        		<mutator>ABS</mutator>
        		<mutator>OBBN</mutator>
        		<mutator>AOD</mutator>
        		<mutator>AOD2</mutator>
        		<mutator>ROR_GT</mutator>
        		<mutator>ROR_GE</mutator>
        		<mutator>ROR_LT</mutator>
        		<mutator>ROR_LE</mutator>
        		<mutator>ROR_EQ</mutator>
        		<mutator>ROR_NE</mutator>
        		<mutator>AOR</mutator>
        		<mutator>UOI</mutator>
        		<mutator>CRCR</mutator>
        		<mutator>CRCR_MUTATOR_ToDecrement</mutator>
        		<mutator>CRCR_MUTATOR_ToIncrement</mutator>
        		<mutator>CRCR_MUTATOR_ToOne</mutator>
        		<mutator>CRCR_MUTATOR_ToZero</mutator>
        		<mutator>CRCR_MUTATOR_ToNegation</mutator>
        		<mutator>OSBN_AND</mutator>
        		<mutator>OSBN_OR</mutator>
        		<mutator>OSBN_XOR</mutator>
        		<mutator>AND</mutator>
    		</mutators>
    	</configuration>
      </plugin>
  ```

Implementation Goals:

- [x] ABS: Replaces a variable by its negation.

- [x] OBBN: Replaces the operators & by | and vice versa.

- [x] AOD: Replaces an arithmetic expression by one of the operand. 

- [x] ROR: Replaces the relational operators with another one. It applies every replacement. 

- [x] AOR: Replaces an arithmetic expression by another one.

- [x] UOI: Replaces a variable with a unary operator or removes an instance of an unary operator.

- [x] CRCR: Replaces a constant a with its negation, or with 1, 0, a + 1, a – 1.

- [x] OSBN: Replaces a shift operator with bitwise operators.

- [x] AND: Remove two arguments in methods.

Test Projects:

- https://github.com/gaul/are-we-consistent-yet

- https://github.com/talios/clojure-maven-plugin

- https://github.com/jiecao-fm/hashids-java

- https://github.com/giltene/PauselessHashMap

- https://github.com/spotify/trickle
