#!/bin/sh

KEYSTORE=signing-jar.keystore

keytool -genkey -alias rivalryui -keystore $KEYSTORE -storepass rivalryui -keypass rivalryui -dname "CN=Jeffrey Thompson, OU=Rivalry.org, O=Rivalry.org, L=Lone Tree, S=CO, C=US"
keytool -selfcert -alias rivalryui -keystore $KEYSTORE -storepass rivalryui -keypass rivalryui
