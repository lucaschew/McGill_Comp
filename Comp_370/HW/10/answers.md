# Homework 10

## Question 1

When dealing with human coded data, it can be tempting to throw out data items that did not have perfect or majority agreement. Why is this a bad practice?

This is a bad practice since it does not solve the underlying problem of having blurry edge cases. Furthermore, it could reduce the quality of the annotation since you are discarding inputs and perspectives.

## Question 2

Consider the following analysis tasks. Rank them by the degree to which they are fishing expeditions. Explain your ranking.

a. Measure the correlation between outside temperature and the amount of foot traffic on St. Laurent on a Saturday night.
b. Identify the kinds of locations where people go on St. Laurent.
c. Identify the dietary preferences of people who shop on St. Laurent.

My ranking would be C -> B -> A in terms of most fishing to least.
I put C first since it is very broad and it could be an unknown result. Furthermore, I don't think there is a relationship between dietary preferences and the location. I put B second since it is slightly open-ended with what the purpose of the question is, but is more focused than C, since it could be correlated. Lastly, A is last since it is very specific with what it wants, therefore being the least fishing-like.

## Question 3

For the most extreme fishing expedition identified in #1, explain how more work in the data annotation phase would avoid/limit the open-ness of the question.

This could be limited by having clear categories for the preferences, rather than having it be open-ended. Furthermore, it could refine the location of rather than the street, it could be a restaurant or maybe by gender.

## Question 4

Diagram and explain how the desire to use automated data annotation can lead to a secondary data science project.

Diagram:

```
+-------------------------+
| Original Data Science   |
| Project                 |
| (e.g., Predictive Model)|
+-------------------------+
        |
        | Need for Annotated Data
        v
+--------------------------+
| Automated Annotation     |
| System                   |
+--------------------------+
        |
        | Develop ML Models
        | for Annotation
        v
+--------------------------+
| Secondary Data Science   |
| Project                  |
| (e.g., Text Classifier)  |
+--------------------------+
        |
        | Collect, Preprocess,
        | Train, and Evaluate
        v
+--------------------------+
| Annotated Data Output    |
+--------------------------+
        |
        | Feed back to primary
        | data science project
        v
+-------------------------+
| Primary Project         |
| (Uses Annotated Data)   |
+-------------------------+
```

An automated data annotation application can become its own project since there are many steps that are needed in order to actually train the model to be usable. Furthermore, data science is the idea of building new tools to aid in data analytics, so it would make sense there.

In order to create the annotation application, we would need to feed the LLM with data and train it until it becomes capable of producing quality annotations, which would eventually be given back to the main project to use as a new tool.

## Question 5

For each question below, identify the type of answer required (definition, process, or motivation) and give a precise answer for a young child audience (say somewhere around 5-6 years old).
a. What is an airplane?
b. Why do we have stoplights?
c. When is it appropriate to yell “Fire” in a crowded room?
d. How do you eat an apple?

A)
Type: Definition
Answer: An airplane is a big flying car that can take us to places far away very quickly.

B)
Type: Motivation
Answer: To keep other cars and people safe.

C)
Type: Process
Answer: You should only yell "Fire" where there is an actual fire to prevent people from getting hurt and so that people can leave safely.

D)
Type: Process
Answer: You wash the apple and then take a bite, then chew, then swallow. Get close to the middle and then rotate the apple, since the middle is hard to chew and not very tasty.
