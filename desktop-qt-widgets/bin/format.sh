 #!/usr/bin/env bash

cd ..
astyle --options="./astyle.conf" --suffix=none --recursive "*.cpp"  "*.h" 