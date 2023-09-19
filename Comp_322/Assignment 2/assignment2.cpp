// Lucas Chew
// 260971542

#include <iostream>
#include <fstream>
#include <sstream>
#include <string.h>

using namespace std;

// Classes and methods
class GitVersion;
void printStartMenu();
void add(string content);
void print();
void load (int version);
void compare(int version1, int version2);
void search (string keyword);
void remove (int version);
size_t hashIt(string s);
void exitCleanUp();

// Static variables
static int nodeCounter;
static int versionCounter;
static GitVersion* head;

// LinkedList Node structure
struct GitVersion {

    size_t hashcode;
    string content;
    int versionID;
    GitVersion* next;

    GitVersion(size_t hashcode, string content) {

        this->hashcode = hashcode;
        this->content = content;
        this->versionID = versionCounter + 1;
        versionCounter++;
        this->next = nullptr;

    }

};

int main() {

    bool exit = false;
    nodeCounter = 0;
    int versionCounter = 1;
    head = nullptr;

    fstream fs;
    stringstream ss;
    string searchString;

    // Print starting menu on launch
    printStartMenu();

    while (!exit) {

        // Get character input and determine what functions to call
        char input = cin.get();

        switch (input) {

            case 'a':

                fs.open("file.txt", ios::in);
                ss << fs.rdbuf();
                fs.close();

                add(ss.str());
                ss.str("");
                break;

            case 'r':

                cout << "Which version would you like to load?" << endl;
                int removeInput;
                cin >> removeInput;
                remove(removeInput);
                break;

            case 'l':

                cout << "Which version would you like to load?" << endl;
                int loadInput;
                cin >> loadInput;
                load(loadInput);
                break;

            case 'p':

                print();
                break;

            case 'c':

                int v1, v2;

                cout << "Please enter the number of the first version to compare:" << endl;
                cin >> v1;

                cout << "Please enter the number of the second version to compare:" << endl;
                cin >> v2;

                compare(v1, v2);
                break;

            case 's':

                cout << "Please enter the keyword that you are looking for:" << endl;
                cin >> searchString;
                search(searchString);
                searchString = "";
                break;

            case 'e':

                exitCleanUp();
                exit = true;
                break;

            default:
                break;

        }

    }

}

void printStartMenu() {

    // Start-up code
    cout << "Welcome to the Comp322 file versioning system!" << endl << endl;
    cout << "To add the content of your file to version control press 'a'" << endl;
    cout << "To remove a version press 'r'" << endl;
    cout << "To load a version press 'l'" << endl;
    cout << "To print to the screen the detailed list of all versions press 'p'" << endl;
    cout << "To compare any 2 versions press 'c'" << endl;
    cout << "To search versions for a keyword press 's'" << endl;
    cout << "To exit press 'e'" << endl;

}

void add(string content) {

    // Create hashcode of content and temp pointer;
    size_t hashcode = hashIt(content);
    GitVersion* temp = head;

    // If version already exists, don't create new version
    while (temp != nullptr) {

        if (temp->hashcode == hashcode) {

            cout << "git322 did not detect any change to your file and will not create a new version." << endl;
            return;
        }

        temp = temp->next;

    }

    // Create new node
    temp = head;
    GitVersion* node = new GitVersion(hashcode, content);

    // If linkedlist is empty, set new node to be head
    // Else, put node at the end of the tail of the list
    if (head == nullptr) {
        head = node;
    } else {

        while (temp->next != nullptr) {
            temp = temp->next;
        }

        temp->next = node;
    }

    nodeCounter++;
    cout << "Your content has been added successfully." << endl;

}

void print() {

    // Print number of versions within the linkedlist
    cout << "Number of versions: " << nodeCounter << endl;
    GitVersion* temp = head;

    // For each node, print out version, hash, and content
    while (temp != nullptr) {

        cout << "Version Number: " << temp->versionID << endl;
        cout << "Hash value: " << temp->hashcode << endl;
        cout << "Content: " << temp->content << endl << endl;

        temp = temp->next;

    }

}

void load (int version) {

    GitVersion* temp = head;
    
    while (temp != nullptr) {

        if (temp->versionID == version) {

            // Open file, delete everything in file, and replace with content
            ofstream writer("file.txt");
            writer << temp->content << endl;
            writer.close();

            cout << "Version " << version << " loaded successfully. Please refresh your text editor to see the changes." << endl;
            return;
        }

        temp = temp->next;

    }

    cout << "Please enter a valid version number. If you are not sure please press 'p' to list all valid version numbers." << endl;

}

void compare(int version1, int version2) {

    // If versions are identical
    if (version1 == version2) {
        cout << "The two versions inputed at the same." << endl;
        return;
    }

    GitVersion* temp = head;
    bool v1Found = false, v2Found = false;
    stringstream *ss1, *ss2;

    // Get version content
    while ((!v1Found || !v2Found) && temp != nullptr) {

        if (temp->versionID == version1) {
            ss1 = new stringstream(temp->content);
            v1Found = true;
        }

        else if (temp->versionID == version2) {
            ss2 = new stringstream(temp->content);
            v2Found = true;
        }

        temp = temp->next;

    }

    // If either version1 or version2 does not exist, print and delete memory
    if ((!v1Found || !v2Found)) {

        if (!v1Found) {
            cout << "Version " << version1 << " does not exist." << endl;
        } else {
            delete ss1;
        }

        if (!v2Found) {
            cout << "Version " << version2 << " does not exist." << endl;
        } else {
            delete ss2;
        }

        return;
    }

    string s1, s2;
    int lineCounter = 1;

    // While both streams have a line, hash and compare both lines
    // print whether they are identical or different
    while (!ss1->eof() && !ss2->eof()) {

        getline(*ss1, s1, '\n');
        getline(*ss2, s2, '\n');

        // If lines are identical
        if (hashIt(s1) == hashIt(s2)) {
            cout << "Line " << lineCounter << ": <Identical>" << endl;
        } else if (s1.length() == 0) {
            // If string 1 is empty, replace it with empty line
            cout << "Line " << lineCounter << ": Empty Line <<>> " << s2 << endl;
        } else if (s2.length() == 0) {
            // If string 2 is empty, replace it with empty line
            cout << "Line " << lineCounter << ": " << s1 << " <<>> Empty Line" << endl;
        } else {
            // If strings are not identical and both are not empty, print both
            cout << "Line " << lineCounter << ": " << s1 << " <<>> " << s2 << endl;
        }
        
        lineCounter++;

    }

    // If only stream1 has a line, print it and have s2 be empty
    while (!ss1->eof()) {

        getline(*ss1, s1, '\n');
        cout << "Line " << lineCounter << ": " << s1 << " <<>> <Empty Line>" << endl;
        
        lineCounter++;

    }

    // If only stream2 has a line, print it and have s1 be empty
    while (!ss2->eof()) {

        getline(*ss2, s2, '\n');
        cout << "Line " << lineCounter << ": <Empty Line> <<>> " << s2 << endl;
        
        lineCounter++;

    }

    // Delete allocated memory
    delete ss1;
    delete ss2;

}

void search (string keyword) {

    GitVersion* temp = head;
    bool found = false;
    size_t pos;

    // For each node in the linkedlist, find whether there is an instance of the keyword
    // If so, print the version, hash, and content
    while (temp != nullptr) {

        pos = temp->content.find(keyword);

        if (pos != string::npos) {

            if (!found) {
                cout << "The keyword '" << keyword << "' has been found in the following version(s):" << endl;
                found = true;
            }

            cout << "Version Number: " << temp->versionID << endl;
            cout << "Hash value: " << temp->hashcode << endl;
            cout << "Content: " << temp->content << endl << endl;

        }

        temp = temp->next;

    }

    // Else, print that no version was found
    if (!found) {
        cout << "Your keyword '" << keyword << "' was not found in any version." << endl;
    }

}

void remove (int version) {

    GitVersion* prev = nullptr;
    GitVersion* temp = head;
    
    // Iterate through linkedlist to find the node with the matching version
    // If the first node is the version, move head to the next and delete the node
    // If the node exists somewhere else, set the previous node's next to the deleted node's next and delete the middle node
    // If the node does not exist, print message with error
    while (temp != nullptr) {

        if (temp->versionID == version) {

            if (prev == nullptr) {

                head = head->next;
                delete temp;

            } else {

                prev->next = temp->next;
                delete temp;

            }

            nodeCounter--;
            cout << "Version " << version << " deleted successfully." << endl;
            return;
        }

        prev = temp;
        temp = temp->next;

    }

    cout << "Please enter a valid version number." << endl;

}

size_t hashIt(string s) {

    return hash<string>{}(s);

}

void exitCleanUp() {

    GitVersion* next = head;

    // For each node in the list, delete the allocated memory
    while (next != nullptr) {

        next = head -> next;
        delete head;
        head = next;

    }

}