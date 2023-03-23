#include <iostream>
#include <fstream>
#include <sstream>

using namespace std;

bool word_diff(std::string word1, std::string word2);
bool classical_file_diff(std::string file1, std::string file2);
std::size_t hash_it (std::string someString);
bool enhanced_file_diff(std::string file1, std::string file2);

void list_mismatched_lines(std::string file1, std::string file2);
void list_mismatched_lines_helper(std::fstream& f1, std::fstream& f2, std::string fileName1, std::string fileName2);

void list_mismatched_words(std::string file1, std::string file2);
void list_mismatched_words_helper(std::fstream& f1, std::fstream& f2, std::string& fileName1, std::string& fileName2, int lineNumber);

bool is_file_empty(std::fstream& f);


int main() 
{
    // Q1
    std::cout << "Q1" << endl;
    std::string str1 = "Hello World";
    std::string str2 = "hEllO World";
    std::string str3 = "World";
    std::string str4 = "Hello World";
    bool result = word_diff(str1, str2); // False
    std::cout << result << endl;
    result = word_diff(str1, str3); // False
    std::cout << result << endl;
    result = word_diff(str1, str4); // True
    std::cout << result << endl;

    // Q2
    std::cout << "Q2" << endl;
    std::string file1 = "./txt_folder/file1.txt";
    std::string file2 = "./txt_folder/file2.txt";
    result = classical_file_diff(file1, file2); // False
    std::cout << result << endl;

    // // Q3
    std::cout << "Q3" << endl;
    std::string mystr = "I love this assignment";
    std::size_t h1 = hash_it (mystr);
    std::cout << h1 << std::endl;

    // Q4
    std::cout << "Q4" << endl;
    result = enhanced_file_diff(file1, file2); // False
    std::cout << result << endl;

    // Q5
    std::cout << "Q5" << endl;
    list_mismatched_lines(file1, file2); // This should print to the screen the mismatched lines

    // Q6
    std::cout << "Q6" << endl;
    list_mismatched_words(file1, file2); // This should print to the screen the mismatched words

}

bool word_diff(std::string word1, std::string word2) {

    // Compare two strings and return true/false
    return word1 == word2;

}

bool classical_file_diff(std::string file1, std::string file2) {

    fstream f1, f2;
    string word1, word2;

    // Open files into fstreams (read and write)
    f1.open(file1, ios::in);
    f2.open(file2, ios::in);
    
    // While both of the streams aren't null, continue
    while(f1 && f2) {

        // Take a word from each stream
        f1 >> word1;
        f2 >> word2;

        // If the words are not equal
        if (!word_diff(word1, word2)) {

            // Close files and return
            f1.close();
            f2.close();

            return false;

        }

    }

    // Close files and return
    f1.close();
    f2.close();

    return true;

}

std::size_t hash_it (std::string someString) {

    // Hash the string given
    return std::hash<std::string>{}(someString);

}

bool enhanced_file_diff(std::string file1, std::string file2) {

    fstream f1, f2;
    ostringstream streamString1, streamString2;
    size_t hash1, hash2;
        
    // Open file streams
    f1.open(file1, ios::in);
    f2.open(file2, ios::in);

    // Use stringstream to read all characters in the file and covert it into a string, to be hashed
    streamString1 << f1.rdbuf() ;
    hash1 = hash_it(streamString1.str());
    
    streamString2 << f2.rdbuf() ;
    hash2 = hash_it(streamString2.str());

    // Close files
    f1.close();
    f2.close();
   
    // Return comparison
    return hash1 == hash2;
    
}

void list_mismatched_lines(std::string file1, std::string file2) {

    fstream f1, f2;
    string fileName1, fileName2;

    // Remove the characters before the last backslash
    fileName1 = file1.substr(file1.find_last_of("/\\") + 1);
    fileName2 = file2.substr(file2.find_last_of("/\\") + 1);

    // Open files
    f1.open(file1);
    f2.open(file2);

    // Call recursive function
    list_mismatched_lines_helper(f1, f2, fileName1, fileName2);

    // Close files
    f1.close();
    f2.close();

}

void list_mismatched_lines_helper(std::fstream& f1, std::fstream& f2, std::string fileName1, std::string fileName2) {

    string line1, line2;

    if (is_file_empty(f1) && is_file_empty(f2)) {
        // If both files are empty, return and break recursion
        return;
    } else if (is_file_empty(f1)) {

        // If only stream1 is ended, get line2 and print
        getline(f2, line2);
        cout << fileName1 + ": " << endl;
        cout << fileName2 + ": " + line2 << endl;

    } else if (is_file_empty(f2)) {

        // If only stream2 is ended, get line1 and print
        getline(f1, line1);
        cout << fileName1 + ": " + line1 << endl;
        cout << fileName2 + ": " << endl;

    } else {

        // Retrieve lines from stream
        getline(f1, line1);
        getline(f2, line2);

        // If the hashed lines are not equal, print the lines
        if (hash_it(line1) != hash_it(line2)) {

            cout << fileName1 + ": " + line1 << endl;
            cout << fileName2 + ": " + line2 << endl;

        }

    }

    // Recursive call
    list_mismatched_lines_helper(f1, f2, fileName1, fileName2);

}

void list_mismatched_words(std::string file1, std::string file2) {

    fstream f1, f2;
    string fileName1, fileName2;

    // Remove the characters before the last backslash
    fileName1 = file1.substr(file1.find_last_of("/\\") + 1);
    fileName2 = file2.substr(file2.find_last_of("/\\") + 1);

    // Open files
    f1.open(file1);
    f2.open(file2);

    list_mismatched_words_helper(f1, f2, fileName1, fileName2, 1);

    // Close files
    f1.close();
    f2.close();

}

void list_mismatched_words_helper(std::fstream& f1, std::fstream& f2, std::string& fileName1, std::string& fileName2, int lineNumber) {

    string line1, line2;

    if (is_file_empty(f1) && is_file_empty(f2)) {
        // If the files are empty, return and break recursion
        return;
    } else if (is_file_empty(f1)) {
        // If file1 is empty, get line from file2 and continue
        line1 = "";
        getline(f2, line2);
    } else if (is_file_empty(f2)) {
        // If file2 is empty, get line from file1 and continue
        getline(f1, line1);
        line2 = "";
    } else {
        // Get line from each stream
        getline(f1, line1);
        getline(f2, line2);

        //compare lines with hashing
        if (hash_it(line1) == hash_it(line2)) {
            list_mismatched_words_helper(f1, f2, fileName1, fileName2, lineNumber+1);
        }
    }

    string word1, word2;
    
    if (line1.size() == 0) {
        
        //If line1 is empty, for every word in line2, print
        istringstream stream2(line2);

        while (stream2 >> word2) {
            cout << fileName1 + ": " + " (line " + to_string(lineNumber) + ")" << endl;
            cout << fileName2 + ": " + word2 + " (line " + to_string(lineNumber) + ")" << endl;
        }

    } else if (line2.size() == 0) {
        
        //If line2 is empty, for every word in line1, print
        istringstream stream1(line1);

        while (stream1 >> word1) {
            cout << fileName1 + ": " + word1 + " (line " + to_string(lineNumber) + ")" << endl;
            cout << fileName2 + ": " + " (line " + to_string(lineNumber) + ")" << endl;
        }
        
    } else {

        // Create stream for both lines
        istringstream stream1(line1);
        istringstream stream2(line2);

        //for each line that is not the same, print
        while (stream1 >> word1 && stream2 >> word2) {

            if (word1 == "") {
                cout << fileName2 + ": " + word2 + " (line " + to_string(lineNumber) + ")" << endl;
            } else if (word2 == "") {
                cout << fileName1 + ": " + word1 + " (line " + to_string(lineNumber) + ")" << endl;
            } else {
                if (hash_it(word1) != hash_it(word2)) {

                    cout << fileName1 + ": " + word1 + " (line " + to_string(lineNumber) + ")" << endl;
                    cout << fileName2 + ": " + word2 + " (line " + to_string(lineNumber) + ")" << endl;

                }
            }
        }

    }

    list_mismatched_words_helper(f1, f2, fileName1, fileName2, lineNumber+1);
}

bool is_file_empty(std::fstream& f)
{
    // Function to check if the file is empty, by checking the current position and the EOF character
    return f.tellg() != 0 && f.peek() == std::fstream::traits_type::eof();
}