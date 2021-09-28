import java.io.OptionalDataException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Cipher {

    static char[] danishAlphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'Æ', 'Ø', 'Å'};

    public static void main(String[] args) {
        /*
        * no space is allowed in the message
         */
        String typeOfCipher = getCipherType(); // which type of cipher you like to use for encoding or decoding
        String encodingOrDecoding = getChoiceEncodeOrDecode(); // do you like to encode a message or decode a ciphered message
        String messageToEncodeOrDecode = getMessage(); // message that you want to encode or decode
        String[] encodedOrDecodedMessage = messageEncoderOrDecoder(messageToEncodeOrDecode, typeOfCipher, encodingOrDecoding);
        printMessage(encodedOrDecodedMessage);

    } //end of main method
    public static String getMessage(){
        //get message to encode or decode
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write the message");
        String message = (scanner.nextLine()).toUpperCase(Locale.ROOT); // users input message will be converted to upper case
        return message;
    }

    public static String getChoiceEncodeOrDecode(){
        // ask if user want to encode a message or decode a message
        Scanner scanner = new Scanner(System.in);
        String encodeOrDecodeMessage = "";
        while (true){
            System.out.println("Do you like ot encode or decode? Type 'Encode' or 'Decode' ");
            encodeOrDecodeMessage = (scanner.nextLine()).toLowerCase(Locale.ROOT);
            if( encodeOrDecodeMessage.equals("encode") || encodeOrDecodeMessage.equals("decode")){
                break;
            }
        }
        return encodeOrDecodeMessage;
    }

    public static String getCipherType(){
        // get the type of cipher user want to use to encode or decode a message
        Scanner scanner = new Scanner(System.in);
        String typeOfCipher = "";
        while (true){
            System.out.println("Which type of Cipher you like to use? Type 'Number' or 'Caesar' or 'Vigenere'");
            typeOfCipher = (scanner.nextLine()).toLowerCase(Locale.ROOT);
            if (  (typeOfCipher).equals("number") || (typeOfCipher).equals("caesar") || (typeOfCipher).equals("vigenere") ){
                break;
            }
        }

        return typeOfCipher;
    }

    public static String[] messageEncoderOrDecoder(String messageToEncodeOrDecode, String typeOfCipher, String encodingOrDecoding){
        // encode or decode any given message
        String[] encodedOrDecodedMessage = new String[messageToEncodeOrDecode.length()];
        if(typeOfCipher.equals("number")){
            encodedOrDecodedMessage = numberCipherEngine(messageToEncodeOrDecode, typeOfCipher, encodingOrDecoding);
        } else if (typeOfCipher.equals("caesar")) {
            encodedOrDecodedMessage = caesarCipherEngine(messageToEncodeOrDecode, typeOfCipher, encodingOrDecoding);
        } else if (typeOfCipher.equals("vigenere")) {
            encodedOrDecodedMessage = vigenereCipherEngine(messageToEncodeOrDecode, typeOfCipher, encodingOrDecoding);
        }
        return encodedOrDecodedMessage;
    }

    public static String[] vigenereCipherEngine(String messageToEncodeOrDecode, String typeOfCipher, String encodingOrDecoding){
        //encode or decode vigenere message
        String[] encodedOrDecodedMessage = new String[messageToEncodeOrDecode.length()];
        String vigenerCipherKey = getVigenereCipherKey(typeOfCipher); // for Vigenere cipher get the key to encode or decode
        if (encodingOrDecoding.equals("encode")) {
            encodedOrDecodedMessage = vigenereEncoder(messageToEncodeOrDecode, vigenerCipherKey);
        } else if(encodingOrDecoding.equals("decode")){
            encodedOrDecodedMessage = vigenereDecoder(messageToEncodeOrDecode, vigenerCipherKey);
        }
        return encodedOrDecodedMessage;
    }

    public static String[] caesarCipherEngine(String messageToEncodeOrDecode, String typeOfCipher, String encodingOrDecoding){
        //encode or decode caesar message
        String[] encodedOrDecodedMessage = new String[messageToEncodeOrDecode.length()];
        String shiftingDirection = getShiftingDirection(typeOfCipher); // for Caesar cipher choose a shifting direction either "Right" or "Left"
        int shiftingValue = getShiftingValue(typeOfCipher); // for Caesar cipher choose a shifting value fx- 2 or 5 or.....
        if (encodingOrDecoding.equals("encode")) {
            encodedOrDecodedMessage = caesarEncoder(messageToEncodeOrDecode, shiftingValue, shiftingDirection);
        } else if(encodingOrDecoding.equals("decode")){
            encodedOrDecodedMessage = caesarDecoder(messageToEncodeOrDecode, shiftingValue, shiftingDirection);
        }
        return encodedOrDecodedMessage;
    }

    public static String[] numberCipherEngine(String messageToEncodeOrDecode, String typeOfCipher, String encodingOrDecoding){
        //encode or decode numbe cipher message
        String[] encodedOrDecodedMessage = new String[messageToEncodeOrDecode.length()];
        if (encodingOrDecoding.equals("encode")) {
            encodedOrDecodedMessage = numberCipherEncoder(messageToEncodeOrDecode);
        } else if(encodingOrDecoding.equals("decode")){
            encodedOrDecodedMessage = numberCipherDecoder(messageToEncodeOrDecode);
        }
        return encodedOrDecodedMessage;
    }

    public static int getShiftingValue(String typeOfCipher){
        // get and return shifting value for Caesar cipher
        Scanner scanner = new Scanner(System.in);
        int shiftingValue = 0;
            do {
                System.out.println("Enter shifting value (between 1 - 28)");
                shiftingValue = scanner.nextInt();
            } while ( shiftingValue < 1 || shiftingValue > 28);
        return shiftingValue;
    }

    public static String getShiftingDirection(String typeOfCipher){
        // get and return shifting direction for caesar cipher
        Scanner scanner = new Scanner(System.in);
        String shiftingDirection = "";
            while (true){
                System.out.println("Please enter shifting direction");
                shiftingDirection = (scanner.nextLine()).toLowerCase(Locale.ROOT);
                if( (shiftingDirection.equals("left") || shiftingDirection.equals("right")) ){
                    break;
                }
            }
        return shiftingDirection;
    }


    public static String getVigenereCipherKey(String typeOfCipher){
        // request the user to enter key for vigenere cipher.
        Scanner scanner = new Scanner(System.in);
        Pattern pattern = Pattern.compile("[A-Za-z]*");
        String vigenereCipherKey = "";
            System.out.println("Please enter key for Vigenere cipher (only english letter allowed)");
            while (true){
                if(scanner.hasNext(pattern)){
                    vigenereCipherKey = scanner.next();
                    vigenereCipherKey = vigenereCipherKey.toUpperCase(Locale.ROOT);
                    break;
                } else {
                    vigenereCipherKey = scanner.next();
                    System.out.println(vigenereCipherKey + " is not allowed. Only english letter allowed");
                }
            }
        return vigenereCipherKey;
    }


    public static String[] vigenereDecoder(String messageToDecode, String vigenereCipherKey){
        // decode a vigenere ciphered message
        String[] decodedVigenereCipheredMessage = new String[messageToDecode.length()];
        for (int i = 0; i < messageToDecode.length(); i++) {
            int asciiValueOfKeyChar = getCharFromVigenereKey(messageToDecode, vigenereCipherKey, i);
            int asciiValueOfMessageChar = messageToDecode.charAt(i);
            char decodedChar = getVigenereDecodedChar(asciiValueOfKeyChar, asciiValueOfMessageChar);
            decodedVigenereCipheredMessage[i] = String.valueOf(decodedChar);
        }
        return decodedVigenereCipheredMessage;
    }

    public static char getVigenereDecodedChar(int asciiValueOfKeyChar, int asciiValueOfMessageChar){
        // retrieve the original char from a specific char of encoded message
        // Example if encoded message = "HOREBRZ", this method with retrieve the actual meaning of char 'H'/'O'/'R'......
        char decodedChar = 'A';
        int decodedCharIndex = (asciiValueOfMessageChar - asciiValueOfKeyChar +26)%26;
        decodedChar = danishAlphabet[decodedCharIndex];
        return decodedChar;
    }

    public static String[]  vigenereEncoder(String messageToEncode, String vigenereCipherKey){
        //encode a vinegere ciphered message
        String[] encodedVigenereCipheredMessage = new String[messageToEncode.length()];
        for (int i = 0; i < messageToEncode.length(); i++) {
            int asciiValueOfKeyChar = getCharFromVigenereKey(messageToEncode, vigenereCipherKey, i);
            int asciiValueOfMessageChar = messageToEncode.charAt(i);
            char encodedChar = getVigenereEncodedChar(asciiValueOfKeyChar, asciiValueOfMessageChar);
            encodedVigenereCipheredMessage[i] = String.valueOf(encodedChar);
        }
        return encodedVigenereCipheredMessage;
    }

    public static char getCharFromVigenereKey(String messageToEncode, String vigenereCipherKey, int indexValue){
        //get a correspondent char from "vigenereCipherKey",
        // Example if key is "BAD" you need to use either B/A/D char as a key to encode a specific char of your message
        // this method will return you the right key char  to use
        char keyChar = 'A';
        int indexValueOfKeyCharToBeUsed = indexValue % vigenereCipherKey.length();
        keyChar = vigenereCipherKey.charAt(indexValueOfKeyCharToBeUsed);
        return keyChar;
    }

    public static char getVigenereEncodedChar(int asciiValueOfKeyChar, int asciiValueOfMessageChar){
        //will return an encoded char by using right key char against a specific char of your message
        char encodedChar = 'A';
        int encodedCharIndex = (asciiValueOfKeyChar + asciiValueOfMessageChar)%26;
        encodedChar = danishAlphabet[encodedCharIndex];
        return encodedChar;
    }

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
        //get the index of char after shifting to right direction
        int indexOfRightShiftedChar;
        if(indexOfCharFromMessage < danishAlphabet.length - shiftingValue){
            indexOfRightShiftedChar = indexOfCharFromMessage + shiftingValue;
        } else {
            indexOfRightShiftedChar = shiftingValue - (29%indexOfCharFromMessage);
        }
        return indexOfRightShiftedChar;
    }

    public static int getIndexOfLeftShiftedChar(int indexOfCharFromMessage, int shiftingValue, String shiftingDirection){
        // get the index of char after shifting to left direction
        int indexOfLeftShiftedChar;
        if(indexOfCharFromMessage>=shiftingValue){
            indexOfLeftShiftedChar = indexOfCharFromMessage-shiftingValue;
        } else {
            indexOfLeftShiftedChar = 29-(shiftingValue-indexOfCharFromMessage);
        }
        return indexOfLeftShiftedChar;
    }

    public static char indexToCharacter(int givenIndex){
        //find the char located at given index from predefined "alphabet" array
        char charAtGivenIndex = danishAlphabet[givenIndex];
        return charAtGivenIndex;
    }

    public static int characterToIndex(char givenChar){
        //find the index value of given character from predefined "alphabet" array
        int IndexOfGivenChar = 0;
        for (int i = 0; i < danishAlphabet.length; i++) {
            if(givenChar == danishAlphabet[i]){
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
        for (String printMessage:message) {System.out.print(printMessage);}
    }


}// end of class Cipher
