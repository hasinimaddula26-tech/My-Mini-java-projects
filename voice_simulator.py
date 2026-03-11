import streamlit as st
import speech_recognition as sr
from gtts import gTTS
import os
import tempfile

st.title("🎙 Voice Based Text Simulator")

recognizer = sr.Recognizer()

if st.button("Start Listening"):
    with sr.Microphone() as source:
        st.info("Listening...")
        audio = recognizer.listen(source)
        try:
            text = recognizer.recognize_google(audio)
            st.success(f"You said: {text}")

            # Convert text to speech
            tts = gTTS(text=f"You said {text}", lang='en')
            temp_file = tempfile.NamedTemporaryFile(delete=False, suffix=".mp3")
            tts.save(temp_file.name)

            # Play the audio in the browser
            audio_file = open(temp_file.name, "rb")
            audio_bytes = audio_file.read()
            st.audio(audio_bytes, format="audio/mp3")

        except Exception as e:
            st.error(f"Sorry, could not understand your voice. Error: {e}")