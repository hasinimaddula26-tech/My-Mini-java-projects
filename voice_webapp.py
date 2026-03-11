# voice_webapp.py
import streamlit as st
import speech_recognition as sr
import pyttsx3

st.title("🎙 Voice Based Text Simulator")

recognizer = sr.Recognizer()
engine = pyttsx3.init()

def speak(text):
    engine.say(text)
    engine.runAndWait()

if st.button("Start Listening"):
    with sr.Microphone() as source:
        st.info("Listening...")
        audio = recognizer.listen(source)
        text = recognizer.recognize_google(audio)
        st.write(f"You said: {text}")
        speak(f"You said {text}")