#include "mainwindow.h"
#include <QApplication>
#include <QDesktopWidget>

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    QRect rec = QApplication::desktop()->screenGeometry();
    MainWindow w;
    int windowHeight = 450;
    int windowWidth = 300;
    w.setGeometry((rec.width() - windowWidth) / 2, (rec.height() - windowHeight) / 2, windowWidth,
                  windowHeight);
    w.show();

    return a.exec();
}
