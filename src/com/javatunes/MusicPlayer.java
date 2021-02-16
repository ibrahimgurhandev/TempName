package com.javatunes;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Collectors;


public class MusicPlayer {
    //FIELDS
    private Collection<Song> songList;
    private PlayerControls controls = new PlayerControls();
    private String genreChoice;
    private Song songChoice;
    private Controller controller = new Controller();
    public static boolean isFinished = false;
    public static boolean isRestarted = false;

    //CONSTRUCTOR
    public MusicPlayer() {
    }

    //BUSINESS METHOD

    public void start() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        while (!isRestarted) {
            genreChoice = controller.promptForGenre(); // will return a genre type
            setSongList(controller.findUserChoice(genreChoice));
            playSong();
        }
    }

    public void playSong() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        try {
            while (!isFinished) {
                printList();
                setSongChoice(controller.promptForSong(getSongList()));
                controls.createClip(getSongChoice().url);
                controls.run(getSongChoice());
            }
        } catch (Exception e) {
            isFinished = false;
            start();
        }
    }

    public void printList() {
        Collection<Song> songs = getSongList();
        for (Song song : songs) {
            System.out.println(song);
        }
    }

    //ACCESSOR METHODS
    public Collection<Song> getSongList() {
        return songList;
    }

    public void setSongList(Collection<Song> songList) {
        this.songList = songList;
    }

    public Song getSongChoice() {
        return songChoice;
    }

    public void setSongChoice(Song songChoice) {
        this.songChoice = songChoice;
    }


}