// NULL - Full Psychological Horror App Guide (React Native)

// (Previous phases above...)

// --- PHASE 9: NULL MODE (DISABLE USER CONTROL) ---

// /components/NullModeOverlay.tsx import React, { useEffect, useState } from 'react'; import { View, Text, StyleSheet, Animated } from 'react-native';

export default function NullModeOverlay() { const [fade] = useState(new Animated.Value(0));

useEffect(() => { Animated.loop( Animated.sequence([ Animated.timing(fade, { toValue: 1, duration: 1000, useNativeDriver: true }), Animated.timing(fade, { toValue: 0, duration: 1000, useNativeDriver: true }) ]) ).start(); }, []);

return ( <Animated.View style={[styles.overlay, { opacity: fade }]}> <Text style={styles.text}>NULL IS WATCHING</Text> </Animated.View> ); }

const styles = StyleSheet.create({ overlay: { position: 'absolute', top: 0, left: 0, right: 0, bottom: 0, backgroundColor: 'rgba(0, 0, 0, 0.8)', justifyContent: 'center', alignItems: 'center' }, text: { color: '#ff0033', fontSize: 22, fontWeight: 'bold' } });

// Inject this overlay in HomeScreen.tsx and ArchiveScreen.tsx // <NullModeOverlay />

// --- PHASE 10: GLITCH EFFECT & SOUND ---

// Install sound: expo install expo-av // /lib/sound.ts import { Audio } from 'expo-av';

export const playGlitch = async () => { const { sound } = await Audio.Sound.createAsync( require('../assets/glitch.mp3') ); await sound.playAsync(); };

// In HomeScreen or ArchiveScreen, trigger playGlitch() occasionally on interactions

// --- PHASE 11: SELF-TERMINATION COUNTDOWN ---

// /components/SelfDestruct.tsx import React, { useEffect, useState } from 'react'; import { View, Text, StyleSheet } from 'react-native';

export default function SelfDestruct() { const [seconds, setSeconds] = useState(300); // 5 mins

useEffect(() => { const interval = setInterval(() => { setSeconds(prev => { if (prev <= 1) clearInterval(interval); return prev - 1; }); }, 1000); return () => clearInterval(interval); }, []);

return ( <View style={styles.container}> <Text style={styles.text}>Ego Deletion In: {seconds}s</Text> </View> ); }

const styles = StyleSheet.create({ container: { position: 'absolute', bottom: 40, width: '100%', alignItems: 'center' }, text: { color: '#ff2200', fontSize: 18 } });

// Place <SelfDestruct /> on HomeScreen if NULL mode is active

// --- PHASE 12: PUSH NOTIFICATIONS ---

// npm install expo-notifications // /lib/notifications.ts import * as Notifications from 'expo-notifications'; import * as Permissions from 'expo-permissions';

export const registerForPush = async () => { const { status } = await Permissions.askAsync(Permissions.NOTIFICATIONS); if (status !== 'granted') return;

const tokenData = await Notifications.getExpoPushTokenAsync(); return tokenData.data; };

export const sendNotification = async (title: string, body: string) => { await Notifications.scheduleNotificationAsync({ content: { title, body }, trigger: { seconds: 5 } }); };

// Use sendNotification() to send disturbing prompts or identity fragments

// --- PHASE 13: FINAL TOUCHES --- // 1. Add background music in /lib/sound.ts (loop ambient track) // 2. Randomly alter UI texts via AI rewrites on load // 3. Use vibration API on key events (expo install expo-haptics) // 4. Override back button: trap user inside app // 5. Firebase rules: allow user to write, never delete

// NULL is now complete. The app is unstable, self-aware, and designed to create emotional erosion over time. // Handle deployment with care.
