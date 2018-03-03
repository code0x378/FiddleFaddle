#!/usr/bin/env bash

# The console colors are ugly, lets disable them even though we
# wont match other FiddleFaddle clones
export DIALOGRC="$PWD/dialogrc"

# Temp file to hold data
responce=/tmp/fiddle-faddle_$$

# Handle ugly shutdowns
trap "rm -f $responce" 0 1 2 5 15

current_question=1

# Question 1
question1="What is the job title of the person in charge of the camera and lighting crews working on a film?"
answers1=("Production_Manager" "Cinematographer" "Unit_Manager" "Gaffer")
correct_question1=2

question2="What is a baby rabbit called?"
answers2=("Kitten" "Pup" "Rabee" "Tuber")
correct_question2=1

question3="In January 1996 Bill Clinton challenged congress to \"Never, ever\" do this again"
answers3=("Kiss_Monica" "Kiss_Hilary" "Shutdown_government" "Run_for_re-election")
correct_question3=3

question4="This Mississippi capital was named for a general who later became president?"
answers4=("Topeka" "Jackson" "Richmond" "Grant")
correct_question4=3

question5="This Honda minivan has a Homeric name"
answers5=("Ulysses" "Troy" "Spartan" "Odyssey")
correct_question5=4

function checkanswer {
eval c=\${correct_question$current_question}

if (($1 == c)); then
    dialog --title "Result" \
    --msgbox "\n CORRECT!" 6 50
else
    dialog --title "Result" \
    --msgbox "\n WRONG!!!" 6 50
fi

if (($current_question == 5)); then
  dialog --title "Bye-Bye" \
    --msgbox "\n Game Over" 6 50
    exit
fi

((current_question++))
next_question current_question
}

function next_question {
eval q=\${question$current_question}

# todo: fix handling spaces
eval a=("\${answers$current_question[@]}")

 dialog --title "Fiddle Faddle" \
    --radiolist "${q}" 13 60 8 \
     1 "${a[0]}" off \
     2 "${a[1]}" off \
     3 "${a[2]}" off \
     4 "${a[3]}" off 2> $responce

   retv=$?
   choice=$(cat $responce)
   [ $retv -eq 1 -o $retv -eq 255 ] && exit

   case $choice in
       1) checkanswer 1
           ;;
       2) checkanswer 2
          ;;
       3) checkanswer 3
          ;;
       4) checkanswer 4
          ;;
   esac
next_question current_question

}
# Main menu
_main () {
  next_question current_question
}

_main
