#!/usr/bin/env python2.7

import curses
import os

questions = {
    1: {
        'question': 'What is the job title of the person in charge of the camera and lighting crews working on a film?',
        'answers': {
            1: 'Production Manager',
            2: 'Cinematographer',
            3: 'Unit Manager',
            4: 'Gaffer'
        },
        'correct': 2
    },
    2: {
        'question': 'What is a baby rabbit called?',
        'answers': {
            1: 'Kitten',
            2: 'Pup',
            3: 'Rabee',
            4: 'Tuber'
        },
        'correct': 1
    },
    3: {
        'question': 'In January 1996 Bill Clinton challenged congress to \"Never, ever\" do this again',
        'answers': {
            1: 'Kiss Monica',
            2: 'Kissc Hilary',
            3: 'Shutdown government',
            4: 'Run for re-election'
        },
        'correct': 3
    },
    4: {
        'question': 'This Mississippi capital was named for a general who later became president?',
        'answers': {
            1: 'Topeka',
            2: 'Jackson',
            3: 'Richmond',
            4: 'Grant'
        },
        'correct': 3
    },
    5: {
        'question': 'This Honda minivan has a Homeric name',
        'answers': {
            1: 'Ulysses',
            2: 'Troy',
            3: 'Spartan',
            4: 'Odyssey'
        },
        'correct': 4
    }
}

current = 1
level = 1
score = 0

screen = curses.initscr()


def check_answer(answer_id):
    global current, score

    os.system('clear')
    if answer_id == questions[current]['correct']:
        score += 1
        return '\nCorrect!\n'
    else:
        return '\nWrong!!!\n'


def exit_game():
    os.system('clear')
    print '\nGame Over - Score: ' + str(score) + '/' + str(current-1) + '\n'
    raw_input('Press enter to continue')
    exit(0)


def main():
    opt = 0
    global current
    global level
    global score

    while opt != ord('5'):

        if current == 6:
            exit_game()

        screen.clear()
        screen.border(0)

        screen.addstr(2, 2, 'Fiddle Faddle')
        screen.addstr(4, 2, 'Level: ' + str(level) + ' - Score: ' + str(score) + '/' + str(current))

        screen.addstr(6, 2, questions[current]['question'])
        screen.addstr(7, 4, "1 - " + questions[current]['answers'][1])
        screen.addstr(8, 4, "2 - " + questions[current]['answers'][2])
        screen.addstr(9, 4, "3 - " + questions[current]['answers'][3])
        screen.addstr(10, 4, "4 - " + questions[current]['answers'][4])
        screen.addstr(11, 4, '5 - Exit')
        screen.refresh()

        opt = screen.getch()

        if opt == ord('1'):
            curses.endwin()
            print check_answer(1)
            raw_input('Press enter to continue')
        if opt == ord('2'):
            curses.endwin()
            print check_answer(2)
            raw_input('Press enter to continue')
        if opt == ord('3'):
            curses.endwin()
            print check_answer(3)
            raw_input('Press enter to continue')
        if opt == ord('4'):
            curses.endwin()
            print check_answer(4)
            raw_input('Press enter to continue')

        current += 1

    curses.endwin()


if __name__ == '__main__':
    main()
