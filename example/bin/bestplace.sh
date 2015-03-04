#! /bin/bash

USER_DIR=/Users/jmthompson
BASE_DIR=${USER_DIR}/Dropbox/SoftwareDev/JavaProjects/rivalry
LIB_DIR=${USER_DIR}/.m2/repository

CP=${BASE_DIR}/classes
CP=${LIB_DIR}/commons-cli/commons-cli/1.2/commons-cli-1.2.jar:${CP}
CP=${LIB_DIR}/org/slf4j/slf4j-api/1.6.1/slf4j-api-1.6.1.jar:${CP}
CP=${LIB_DIR}/commons-io/commons-io/2.0.1/commons-io-2.0.1.jar:${CP}
CP=${LIB_DIR}/net/sourceforge/htmlunit/htmlunit/2.9/htmlunit-2.9.jar:${CP}
CP=${LIB_DIR}/net/sourceforge/htmlunit/htmlunit-core-js/2.9/htmlunit-core-js-2.9.jar:${CP}
CP=${LIB_DIR}/commons-codec/commons-codec/1.4/commons-codec-1.4.jar:${CP}
CP=${LIB_DIR}/org/apache/httpcomponents/httpclient/4.1.2/httpclient-4.1.2.jar:${CP}
CP=${LIB_DIR}/org/w3c/css/sac/1.3/sac-1.3.jar:${CP}
CP=${LIB_DIR}/commons-logging/commons-logging/1.1.1/commons-logging-1.1.1.jar:${CP}
CP=${LIB_DIR}/commons-lang/commons-lang/2.6/commons-lang-2.6.jar:${CP}
CP=${LIB_DIR}/org/apache/httpcomponents/httpcore/4.1.2/httpcore-4.1.2.jar:${CP}
CP=${LIB_DIR}/org/apache/httpcomponents/httpmime/4.1.2/httpmime-4.1.2.jar:${CP}
CP=${LIB_DIR}/commons-collections/commons-collections/3.2.1/commons-collections-3.2.1.jar:${CP}
CP=${LIB_DIR}/net/sourceforge/cssparser/cssparser/0.9.5/cssparser-0.9.5.jar:${CP}
CP=${LIB_DIR}/xerces/xercesImpl/2.9.1/xercesImpl-2.9.1.jar:${CP}
CP=${LIB_DIR}/net/sourceforge/nekohtml/nekohtml/1.9.15/nekohtml-1.9.15.jar:${CP}
CP=${LIB_DIR}/xalan/xalan/2.7.1/xalan-2.7.1.jar:${CP}
CP=${LIB_DIR}/com/google/guava/guava/10.0.1/guava-10.0.1.jar:${CP}
CP=${LIB_DIR}/org/seleniumhq/selenium/selenium-api/2.14.0/selenium-api-2.14.0.jar:${CP}
CP=${LIB_DIR}/org/seleniumhq/selenium/selenium-htmlunit-driver/2.14.0/selenium-htmlunit-driver-2.14.0.jar:${CP}
CP=${LIB_DIR}/org/seleniumhq/selenium/selenium-support/2.14.0/selenium-support-2.14.0.jar:${CP}

OUTPUT_FILE=bestplace.out

java -cp ${CP} org.rivalry.example.bestplace.BestPlaceNameDataCollectorMain &> bestplace0.out
java -cp ${CP} org.rivalry.example.bestplace.BestPlaceDataCollectorMain &> ${OUTPUT_FILE}

echo "line count: " `cat ${OUTPUT_FILE} | wc -l`

LINE_COUNT=`cat ${OUTPUT_FILE} | wc -l`
echo "LINE_COUNT = " ${LINE_COUNT}

if [ ${LINE_COUNT} -eq 278 ];
then
	cp -f BestPlaceRivalryData.xml ${USER_DIR}/Dropbox/Public/rivalry 
fi
