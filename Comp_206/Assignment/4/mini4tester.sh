#!/bin/bash
### WARNING !!!!! STUDENTS MUST NOT CHANGE/EDIT THIS FILE !!!
### You might accidentally delete/change your files or change the intention of the test case
###   if you make changes to this sript without understanding the consequences.
### This script is intended to be executed in mimi using the bash shell.

check_for_source()
{
    if [[ ! -f "$1" ]]
    then
        echo "Source file '$1' not found. Please make sure to upload the mini tester script into the same folder as your source files."
        exit 1;
    fi
}

print_and_run()
{
    echo "\$ $1"
    bash -c "$1"
    echo -e "exit code: $?"
}

create_random_number()
{
    max_length=$1
    nb_bytes=$((max_length/2))
    [[ $nb_bytes < 1 ]] && nb_bytes=1
    random_number=$(dd if=/dev/urandom ibs=1 count=$((2*nb_bytes)) 2>/dev/null | xxd -p | sed -e 's/[a-f]//g' -e 's/^0\+//' | tr -d '\n')
    random_number=${random_number::$max_length}
    echo ${random_number:-0}
}

check_for_source "scalc.c"

# BEGIN SETUP
sourcedir=$PWD
tmpdir=/tmp/__tmp_comp206_${LOGNAME}
mkdir -p "$tmpdir"
cp scalc.c "$tmpdir"
cd "$tmpdir"

echo "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  scalc.c tests @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"

echo
echo "[[[[ test case 0: compiling scalc.c ]]]]"
echo "********************************************************************************"
print_and_run "gcc -o scalc scalc.c"
echo "********************************************************************************"
echo
echo "[[[[ test case 1: EXPECTED TO FAIL - missing parameter ]]]]"
echo "********************************************************************************"
print_and_run "./scalc 99 99"
echo "********************************************************************************"
echo
echo "[[[[ test case 2: EXPECTED TO FAIL - operator not supported ]]]]"
echo "********************************************************************************"
print_and_run "./scalc 99 a 99"
echo '********************************************************************************'
echo
echo "[[[[ test case 3: EXPECTED TO FAIL - floating point numbers not supported ]]]]"
echo '********************************************************************************'
print_and_run "./scalc 99.12 + 99"
echo '********************************************************************************'
echo
echo "[[[[ test case 4: EXPECTED TO WORK ]]]]"
echo '********************************************************************************'
print_and_run "./scalc 0 + 0"
echo
echo "[[[[ test case 5: EXPECTED TO WORK ]]]]"
echo '********************************************************************************'
print_and_run "./scalc 9 + 27"
echo
echo "[[[[ test case 6: EXPECTED TO WORK ]]]]"
echo '********************************************************************************'
print_and_run "./scalc 805 + 73"
echo
echo "[[[[ test case 7: EXPECTED TO WORK ]]]]"
echo '********************************************************************************'
print_and_run "./scalc 39968526 + 261245131"
echo
echo "[[[[ test case 8: EXPECTED TO WORK ]]]]"
echo '********************************************************************************'
print_and_run "./scalc 94563404595414669733481804 + 63063878118515146953239005674188"
echo
echo "[[[[ test case 9: EXPECTED TO WORK ]]]]"
echo '********************************************************************************'
print_and_run "./scalc 337037162655434742172441990005926504627693405062483683481860135421836363885337604476946522829477416487540029564973288285848121501356203831763979353004924770487720185698467151426265434969968783905585811658254753969254088708397977743437345977612770066318929531032426870733410695574460760877864982170018883020301894366421019929336896583656139708796919654043894918310206312955194152961853956972053417581974812378010468215144320879296305235721982264778028844210143325516749821842727625788193008291557371149861033157785400682438615743716585218077236637385527924512517666296496042663177106329406843379423333933197603779667883138817048875787934960714820949243561369745476745169426269043089358101253252961332674755749019583446697081193046739283000512320707580401271575922727927414642017149495796655697003859436074734672473929101589803367493579567077014669236676267092554548103395541458601861931697242692530606284895497919963517250660700604099041412805987327611473073877946189471478740056665096790009155522642 + 808430029611407314542383969124972191702228419281927093842620909668530397950728920217586880096692520691422391259040947261532998079642387051795906066539542102088422262726482720987963597285384373113071520217386144151075544473966115391908967194283950454314016504743465278172912703218309242036995980536710664051188723261203124816514475015911959704775795191903948004836260353862572279261151874512681238491605264990929238879454279724296653989705973119885193672059867505326298094015818053690368476755148599715906773415284443810425550421727365475999535451658786914168200421402449424901290952774281016832107844697722094282792337238440344494506525260346224537135957269954154547871785330315717886322017410402146248477946726821382888416795799967165566548680285551332051143725540120030186418132494037374250501817864047350075307770556457476696430614911753784033504116472314235599975868264139698258271761054818196677464066436810208239585338499857303335261578324413171744621122145917338512342253982972461548138356425"

# CLEAN UP
rm -r "$tmpdir"