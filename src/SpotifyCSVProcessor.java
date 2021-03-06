/* Class: CISC 3130
 * Section: MY9 (Monday and Wednesday)
 * EmplId: 23794282
 * Name: Sean Wy Tze Yap
*/

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class SpotifyCSVProcessor {
    // constructor
    public SpotifyCSVProcessor() {
	songs = new ArrayList<String>();
	artists = new ArrayList<String>();
    }
    // read from a file from a filepath as argument
    public void read(String filepath) throws IOException {
        Scanner in = new Scanner(new File(filepath));
	while (in.hasNext()) {
	    String currentSong = in.nextLine();
	    String[] songData = currentSong.split(",");

	    // Below is the Algorithms to retrieve the data from Spotify csv 
	    // count from backwards. minus 2 because artist is the last third 
	    int artistNameIndex = (songData.length - 1) - 2;
	    String artistName = songData[artistNameIndex];
	    String songName = ""; 	

	    // typically songData has 5 elements 
	    // but if song name has comma, songData will have more than 5 elements
	    // check to make sure we do not lose a part of the name 
	    if (songData.length > 5) {
	        // artistName is the third element
	        // minus first element using third element to get how many part second element (song name) has
	        int parts = artistNameIndex - 1;
		// adding all parts of the song together as a string 
		// second element index starts with 1
	   	for (int i=1; i<=parts; i++) { 
		    // adding back the comma split method removed 
		    // ending doesnt have comma so we need to check for that to prevent adding wrong comma
	            if (i != parts)
		 	songName += songData[i] + ",";
		    else 
			songName += songData[i];	    
		}
	    } 
	    // song has no comma
	    else {
		// song name is before artist name 
		songName = songData[artistNameIndex - 1];
	    }
	   
	    songName = songName.replace("\"", "");

            // check if arraylist contains the song; 
	    if (songs.contains(songName)) { 
		// has duplicates so do not add it to the arraylist
		continue;
	    }
	    else {
	        // adding respective song name and artist to the parallel arraylist
	        songs.add(songName);
	        artists.add(artistName.replace("\"", ""));
	    }
	}
    }

    public void displayList() {
	for (int i=0; i<songs.size(); i++) 
	    System.out.println(songs.get(i) + "  by  " + artists.get(i)); 
    }
    // sort arraylist 
    public void sort() {
	int size = artists.size();
	for (int current=0; current<size-1; current++) {
	    int smallestValueIndex = current;
	    for (int target=current+1; target<size; target++) {
		String smallestValue = songs.get(smallestValueIndex).toLowerCase();
		String targetValue = songs.get(target).toLowerCase();
		int diff = smallestValue.compareTo(targetValue);
		if (diff < 0)
		    continue;
		else
		    smallestValueIndex = target;
	    }
    	    String oldSong = songs.get(current);
	    String newSong = songs.get(smallestValueIndex);
	    songs.set(current, newSong);
	    songs.set(smallestValueIndex, oldSong);
	
	    String oldArtist = artists.get(current);
	    String newArtist = artists.get(smallestValueIndex);
	    artists.set(current, newArtist);
	    artists.set(smallestValueIndex, oldArtist);
	}
    }
    public int getSize() { return songs.size(); } 
    public String getSong(int index) { 
	// check if index is not out of bounds  
	if (index < getSize()) 
	    return songs.get(index);
	else 
	    return null;  
    }
    public String getArtist(int index) {
	// check if index is not out of bounds
	if (index < getSize())
	    return artists.get(index);
	else
	    return null;
    } 
    // field
    private ArrayList<String> songs;
    private ArrayList<String> artists;
}

