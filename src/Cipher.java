import java.io.OptionalDataException;
import java.util.Arrays;

public class Cipher {
    static char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'æ', 'ø', 'å'};
    public static void main(String[] args) {

        String messageToEncode = "goodmorning";
        String messageToDecode = "lttirtwsnsl";

/*        String[] encodeNumberCipheredMessage = numberCipherEncoder(messageToEncode);
        printMessage(encodedMessage);*/


/*        String[] decodedNumberCipheredMessage = numberCipherDecoder(messageToDecode);
        printMessage(decodedMessage);*/



/*        int shiftingValue = 5;
        String shiftingDirection = "right"; // shifting direction can be either "right" or "left"
        String[] encodedCaesarCipheredMessage = caesarEncoder(messageToEncode, shiftingValue, shiftingDirection);
        printMessage(encodedCaesarCipheredMessage);*/

        int shiftingValue = 5;
        String shiftingDirection = "right"; // shifting direction can be either "right" or "left"
        String[] decodedCaesarMessage = caesarDecoder(messageToDecode, shiftingValue, shiftingDirection);
        printMessage(decodedCaesarMessage);





    } //end of main method

    public static String[] caesarDecoder(String MessageToDecode, int shiftingValue, String shiftingDirection){
        String[] decodedCaesarCipheredMessage = new String[MessageToDecode.length()];
        for (int i = 0; i < MessageToDecode.length(); i++) {
            char charFromMessage = MessageToDecode.charAt(i);
            int indexOfCharFromMessage = characterToIndex(charFromMessage);
            int indexOfOriginalChar;
            if(shiftingDirection.equals("right")){ //Note: as right shifting was used during encoding we have to call left shifting method to decode the message
                indexOfOriginalChar = getIndexOfLeftShiftedChar(indexOfCharFromMessage, shiftingValue, shiftingDirection);
            } else {
                indexOfOriginalChar = getIndexOfRightShiftedChar(indexOfCharFromMessage, shiftingValue, shiftingDirection);
            }
            char originalChar = indexToCharacter(indexOfOriginalChar);
            decodedCaesarCipheredMessage[i] = String.valueOf(originalChar);
        }
        return decodedCaesarCipheredMessage;
    }

    public static String[] caesarEncoder(String messageToEncode, int shiftingValue, String shiftingDirection){
        //encode a message based on modified caesar cipher. Shifting value and shifting direction are changeable in this encoder
        String[] encodedCaesarCipheredMessage = new String[messageToEncode.length()];
        int indexOfShiftedChar; //
        for (int i = 0; i < messageToEncode.length(); i++) {
            char charFromMessage = messageToEncode.charAt(i);
            int indexOfCharFromMessage = characterToIndex(charFromMessage);
            if(shiftingDirection.equals("right")){
                indexOfShiftedChar  = getIndexOfRightShiftedChar(indexOfCharFromMessage, shiftingValue, shiftingDirection);
            } else {
                indexOfShiftedChar  = getIndexOfLeftShiftedChar(indexOfCharFromMessage, shiftingValue, shiftingDirection);
            }
            char encodedChar = indexToCharacter(indexOfShiftedChar);
            encodedCaesarCipheredMessage[i] = String.valueOf(encodedChar);
        }
        return encodedCaesarCipheredMessage;

    }

    public static int getIndexOfRightShiftedChar(int indexOfCharFromMessage, int shiftingValue, String shiftingDirection){
        int indexOfRightShiftedChar;
        if(indexOfCharFromMessage < alphabet.length - shiftingValue){
            indexOfRightShiftedChar = indexOfCharFromMessage + shiftingValue;
        } else {
            indexOfRightShiftedChar = shiftingValue - (29%indexOfCharFromMessage);
        }
        return indexOfRightShiftedChar;
    }

    public static int getIndexOfLeftShiftedChar(int indexOfCharFromMessage, int shiftingValue, String shiftingDirection){
        int indexOfLeftShiftedChar;
        if(indexOfCharFromMessage>=shiftingValue){
            indexOfLeftShiftedChar = indexOfCharFromMessage-shiftingValue; //in original cipher default shifting is 3, therefore we deduct index by 3
        } else {
            indexOfLeftShiftedChar = 29-(shiftingValue-indexOfCharFromMessage);
        }
        return indexOfLeftShiftedChar;
    }

    public static char indexToCharacter(int givenIndex){
        //find the char located at given index from predefined "alphabet" array
        char charAtGivenIndex = alphabet[givenIndex];
        return charAtGivenIndex;
    }

    public static int characterToIndex(char givenChar){
        //find the index value of given character from predefined "alphabet" array
        int IndexOfGivenChar = 0;
        for (int i = 0; i < alphabet.length; i++) {
            if(givenChar == alphabet[i]){
                IndexOfGivenChar = i;
            }
        }
        return IndexOfGivenChar;
    }

    public static String[] numberCipherDecoder(String messageToDecode){
        //decode a coded number message and put the whole message in a char array
        String[] messageToDecodeToArray = messageToDecode.split(";");
        String[] decodedMessage = new String[messageToDecodeToArray.length];
        for (int i = 0; i < messageToDecodeToArray.length; i++) {
            int codedNumber = Integer.parseInt(messageToDecodeToArray[i]);
            char decodedChar = indexToCharacter(codedNumber-1); // "codedNumber-1" indicate index of a specific char of the original message
            decodedMessage[i] = String.valueOf(decodedChar);
        }
        return decodedMessage;
    }

    public static String[] numberCipherEncoder(String messageToEncode){
        //encode a string message to coded number message and make an array with each number followed by a semicolon in next index
        String encodedMessage[] = new String[(messageToEncode.length()*2)-1]; //after every number we will add ";", therefore size is double the message size minus 1
        int j = 0; // to refer to index value of encoded number
        int k = 1; // to refer to index value for ";"
        for (int i = 0; i < messageToEncode.length(); i++) {
            int encodedNumberIndex = characterToIndex(messageToEncode.charAt(i));
            int encodedNumber = encodedNumberIndex+1;
            if(j < encodedMessage.length-2){
                encodedMessage[j] = String.valueOf(encodedNumber);
                encodedMessage[k] = ";";
                j = j+2;
                k = k+2;
            } else {
                encodedMessage[j] = String.valueOf(encodedNumber); // after the last number no need to input ";"
            }
        }
        return encodedMessage;
    }

    public static void printMessage(String[] message) {
        // print the encoded or decoded message
        for (String printMessage:message) {
            System.out.print(printMessage);
        }
    }


}// end of class Cipher
