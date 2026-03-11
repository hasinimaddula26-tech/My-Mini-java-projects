import speech_recognition as sr
import pyttsx3

# Initialize
recognizer = sr.Recognizer()
engine = pyttsx3.init()

def speak(text):
    engine.say(text)
    engine.runAndWait()

def listen():
    with sr.Microphone() as source:
        print("Listening...")
        audio = recognizer.listen(source)
        try:
            text = recognizer.recognize_google(audio)
            print(f"You said: {text}")
            return text
        except:
            speak("Sorry, I didn't catch that.")
            return None

# Example logic
while True:
    command = listen()
    if command:
        if "hello" in command.lower():
            speak("Hello! How can I help you?")
        elif "exit" in command.lower():
            speak("Goodbye!")
            break
        else:
            speak("I’m still learning to respond to that.")