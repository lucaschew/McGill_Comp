// Lucas Chew
// 260971542

#include <iostream>
#include <fstream>
#include <sstream>
#include <string.h>

using namespace std;

// Classes and methods
class Node;
class LinkedList;
class Git322;
class EnhancedGit322;
size_t hashIt(string s);

// Node structure
struct Node {

    Node* next;
    size_t hashcode;
    string content;
    int versionID;

    Node(string content, int version) {

        this->hashcode = hashIt(content);
        this->content = content;
        this->versionID = version;
        this->next = nullptr;

    }
    
};

struct LinkedList {

    private: 
        Node* head;
        int size;
    
    public:
        LinkedList() {
            head = nullptr;
            size = 0;
        }

        // Function to get size
        int getSize() {
            return this->size;
        }

        bool add(Node* n) {

            // If list is empty, add to list
            if (head == nullptr) {

                head = n;

            } else {

                Node* itr = head;
                Node* prev;

                // Check if node is null
                while (itr != nullptr) {

                    // Check if the node already exists
                    if (itr->hashcode == n->hashcode)
                        return false;

                    // If the next node is null, stop
                    if (itr->next == nullptr)
                        break;

                    // Else, iterate to next node
                    itr = itr->next;
                }

                // If not in list, add
                itr->next = n;

            }

            size++;
            return true;

        }

        bool remove(int version) {

            Node* prev = nullptr;
            Node* itr = head;
            
            // while node is not null
            while (itr != nullptr) {

                // Check if the node is the correct version
                // If so, remove
                // Else, iterate
                if (itr->versionID == version) {

                    if (prev == nullptr) {

                        head = head->next;

                    } else {

                        prev->next = itr->next;


                    }

                    delete itr;
                    size--;
                    return true;
                }

                prev = itr;
                itr = itr->next;

            }

            return false;

        }

        Node* search(int version) {

            Node* itr = head;

            // Check if node is null
            // If not, check if the node has identical version number
            // Else, iterate    
            while (itr != nullptr) {

                if (itr->versionID == version) {

                    return itr;

                }

                itr = itr->next;

            }

            return nullptr;

        }

        void exitCleanUp() {

            Node* next = head;

            // For each node in the list, delete the allocated memory
            while (next != nullptr) {

                next = head -> next;
                delete head;
                head = next;

        }

}

};

struct Git322 {

    protected:
        int versionCounter;
        LinkedList* myList;

    public:

        Git322() {
            versionCounter = 1;
            myList = new LinkedList();
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

        bool add(string content) {

            // Create new node
            Node* node = new Node(content, versionCounter);

            // Attempt to add to node.
            // If node was added (true), increment version counter
            if (myList->add(node)) {
                versionCounter++;
                cout << "Your content has been added successfully." << endl;
                return true;
            } else {
                // Else (false), give error message
                cout << "git322 did not detect any change to your file and will not create a new version." << endl;
                return false;
            }

        }

        void print() {

            // Print number of versions within the linkedlist
            cout << "Number of versions: " << myList->getSize() << endl;
            int currentNode = 1;

            // Iterate through each possible version number
            while (currentNode < versionCounter) {

                Node* temp = myList->search(currentNode);

                // If the version exists, print the version, hash, and content
                if (temp != nullptr) {

                    cout << "Version Number: " << temp->versionID << endl;
                    cout << "Hash value: " << temp->hashcode << endl;
                    cout << "Content: " << temp->content << endl << endl;

                }

                currentNode++;

            }

        }
        
        bool load (int version) {
        
            Node* temp = myList->search(version);

            // If node is not null, iterate
            if (temp != nullptr) {

                // Open file, delete everything in file, and replace with content
                ofstream writer("file.txt");
                writer << temp->content << endl;
                writer.close();

                cout << "Version " << version << " loaded successfully. Please refresh your text editor to see the changes." << endl;
                return true;

            } else {

                cout << "Please enter a valid version number. If you are not sure please press 'p' to list all valid version numbers." << endl;
                return false;

            }

        }

        bool compare(int version1, int version2) {

            // If versions are identical
            if (version1 == version2) {
                cout << "The two versions inputed at the same." << endl;
                return true;
            }

            Node *a, *b;
            stringstream *ss1, *ss2;

            // Search LinkedList for versions
            a = myList->search(version1);
            b = myList->search(version2);

            // If either version1 or version2 does not exist, give error
            if (a == nullptr || b == nullptr) {

                if (a == nullptr) {
                    cout << "Version " << version1 << " does not exist." << endl;
                }

                if (b == nullptr) {
                    cout << "Version " << version2 << " does not exist." << endl;
                }

                return false;
            }

            ss1 = new stringstream(a->content);
            ss2 = new stringstream(b->content);

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

            return true;

        }

        bool search (string keyword) {

            bool found = false;
            int counter = 1;

            // For each node in the linkedlist, 
            while(counter < versionCounter) {

                Node* temp = myList->search(counter);

                if (temp != nullptr) {

                    // Find whether there is an instance of the keyword
                    size_t pos = temp->content.find(keyword);

                    if (pos != string::npos) {

                        // If so, print the version, hash, and content
                        if (!found) {
                            cout << "The keyword '" << keyword << "' has been found in the following version(s):" << endl;
                            found = true;
                        }

                        cout << "Version Number: " << temp->versionID << endl;
                        cout << "Hash value: " << temp->hashcode << endl;
                        cout << "Content: " << temp->content << endl << endl;

                    }

                }

                counter++;

            }

            // Else, print that no version was found
            if (!found) {
                cout << "Your keyword '" << keyword << "' was not found in any version." << endl;
                return false;
            }

            return true;

        }

        bool remove (int version) {

            // Call LinkedList function to remove
            // If removed (true), give message
            // If not (false), give error
            if (myList->remove(version)) {
                cout << "Version " << version << " deleted successfully." << endl;
                return true;
            } else {
                cout << "Please enter a valid version number." << endl;
                return false;
            }
        }

        void exitCleanUp() {

            // Call LinkedList memory clean-up
            myList->exitCleanUp();
            // Delete LinkedList memory
            delete myList;

        }

};

struct EnhancedGit322 : public Git322 {

    EnhancedGit322() : Git322() {
        
        // Open a file
        ifstream file;
        file.open("storage.txt");

        // Check if file exists
        if (file) {

            string str;

            // While the first line isn't empty
            while (getline(file, str) && str != "") {
            
                // Get version number from first line, and loop until the ending string of "//<--end-->//"
                int version = stoi(str);
                string content = "";

                while (getline(file, str) && str != "//<--end-->//") {
                    content += str + '\n';
                }

                // Remove last newline character
                content.pop_back();

                // Create new node and add to list
                myList->add(new Node(content, version));
                // Set version counter to the max between itself +1 or the node's version +1
                versionCounter = max(versionCounter+1, version+1);
            }

        }

        file.close();

    }

    void exitCleanUp() {

        ofstream file;
        string str;
        int counter = 0;
        
        // Open file and clear file
        file.open("storage.txt", ofstream::trunc);

        // While the counter is less than the version counter
        while(counter < versionCounter) {

                // Search for node with version number
                Node* temp = myList->search(counter);

                // If not null, write to file
                if (temp != nullptr) {

                    file << to_string(temp->versionID) + "\n";
                    file << temp->content + "\n";
                    file << "//<--end-->//\n";

                }

                counter++;

            }

        // Adding overridden features from Git
        myList->exitCleanUp();
        delete myList;

    }


};

int main() {

    bool exit = false;
    fstream fs;
    stringstream ss;
    string searchString;

    EnhancedGit322* git = new EnhancedGit322();

    // Print starting menu on launch
    git->printStartMenu();

    while (!exit) {

        // Get character input and determine what functions to call
        char input = cin.get();

        switch (input) {

            case 'a':

                fs.open("file.txt", ios::in);
                ss << fs.rdbuf();
                fs.close();

                git->add(ss.str());
                ss.str("");
                break;

            case 'r':

                cout << "Which version would you like to load?" << endl;
                int removeInput;
                cin >> removeInput;
                git->remove(removeInput);
                break;

            case 'l':

                cout << "Which version would you like to load?" << endl;
                int loadInput;
                cin >> loadInput;
                git->load(loadInput);
                break;

            case 'p':

                git->print();
                break;

            case 'c':

                int v1, v2;

                cout << "Please enter the number of the first version to compare:" << endl;
                cin >> v1;

                cout << "Please enter the number of the second version to compare:" << endl;
                cin >> v2;

                git->compare(v1, v2);
                break;

            case 's':

                cout << "Please enter the keyword that you are looking for:" << endl;
                cin >> searchString;
                git->search(searchString);
                searchString = "";
                break;

            case 'e':

                git->exitCleanUp();
                delete git;
                exit = true;
                break;

            default:
                break;

        }

    }

};

size_t hashIt(string s) {

    return hash<string>{}(s);

}