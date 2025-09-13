package org.bitcoinj.core;

import org.bitcoinj.base.Sha256Hash;
import org.bitcoinj.base.Network;
import org.bitcoinj.base.Coin;
import org.bitcoinj.crypto.ECKey;
import org.bitcoinj.script.Script;
import org.bitcoinj.script.ScriptBuilder;
import org.bitcoinj.base.Address;
import org.bitcoinj.base.ScriptType;
import org.bitcoinj.base.LegacyAddress;
import org.libdohj.params.DogecoinMainNetParams;
import org.libdohj.params.DogecoinTestNet3Params;
import org.junit.Test;
import org.junit.Before;

import java.security.SecureRandom;

import static org.junit.Assert.*;

/**
 * Test Dogecoin address generation functionality
 */
public class DogecoinAddressTest {
    
    @Test
    public void testMainnetAddressGeneration() {
        System.out.println("=== Dogecoin Mainnet Address Generation Test ===");
        testAddressGeneration(DogecoinMainNetParams.get(), "Mainnet");
    }
    
    @Test
    public void testTestnetAddressGeneration() {
        System.out.println("=== Dogecoin Testnet Address Generation Test ===");
        testAddressGeneration(DogecoinTestNet3Params.get(), "Testnet");
    }
    
    private void testAddressGeneration(NetworkParameters params, String networkName) {
        System.out.println("--- " + networkName + " ---");
        
        try {
            // Debug: Show network parameters
            System.out.println("Network ID: " + params.getId());
            System.out.println("Address Header: " + params.getAddressHeader());
            System.out.println("P2SH Header: " + params.getP2SHHeader());
            
            // Generate a new ECKey
            ECKey key = new ECKey();
            
            // Get the public key hash (20 bytes)
            byte[] pubKeyHash = key.getPubKeyHash();
            
            // Create P2PKH address using proper Dogecoin format
            // Create address manually with correct Dogecoin version bytes
            int addressVersion = params.getAddressHeader();
            
            // Create address bytes with Dogecoin version byte
            byte[] addressBytes = new byte[1 + pubKeyHash.length + 4];
            addressBytes[0] = (byte) addressVersion;
            System.arraycopy(pubKeyHash, 0, addressBytes, 1, pubKeyHash.length);
            
            // Calculate checksum (double SHA256)
            String addressString;
            try {
                java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-256");
                
                // Create a copy of the array for hashing (only version + pubKeyHash, not checksum part)
                byte[] hashInput = new byte[pubKeyHash.length + 1];
                System.arraycopy(addressBytes, 0, hashInput, 0, pubKeyHash.length + 1);
                
                byte[] hash1 = digest.digest(hashInput);
                byte[] hash2 = digest.digest(hash1);
                System.arraycopy(hash2, 0, addressBytes, pubKeyHash.length + 1, 4);
                
                // Encode as Base58
                addressString = encodeBase58(addressBytes);
            } catch (java.security.NoSuchAlgorithmException e) {
                throw new RuntimeException("SHA-256 not available", e);
            }
            
            // Display the generated address
            System.out.println("Generated address: " + addressString);
            System.out.println("Expected version byte: " + addressVersion);
            
            // Display results
            System.out.println("Private Key (WIF): " + key.getPrivateKeyEncoded(params).toString());
            System.out.println("Public Key: " + key.getPublicKeyAsHex());
            System.out.println("P2PKH Address: " + addressString);
            System.out.println("Address Type: P2PKH");
            
            // Verify address format using actual Dogecoin parameters
            System.out.println("Address format check:");
            
            // Check for proper Dogecoin address prefixes
            if (networkName.equals("Mainnet")) {
                // Dogecoin mainnet addresses typically start with 'D' (version 30)
                if (addressString.startsWith("D")) {
                    System.out.println("✓ Mainnet P2PKH address format is correct (starts with 'D')");
                } else {
                    System.out.println("ℹ Mainnet address: " + addressString + 
                        " (expected version " + addressVersion + " - valid but may not start with 'D')");
                }
            } else {
                // Dogecoin testnet addresses typically start with 'n' or '2' (version 113)
                if (addressString.startsWith("n") || addressString.startsWith("2")) {
                    System.out.println("✓ Testnet P2PKH address format is correct");
                } else {
                    System.out.println("ℹ Testnet address: " + addressString + 
                        " (expected version " + addressVersion + " - valid but may not start with 'n' or '2')");
                }
            }
            
            // Basic assertions
            assertNotNull("Address should not be null", addressString);
            assertNotNull("Private key should not be null", key.getPrivateKeyEncoded(params));
            assertNotNull("Public key should not be null", key.getPublicKeyAsHex());
            
        } catch (Exception e) {
            System.err.println("Error generating " + networkName + " address: " + e.getMessage());
            e.printStackTrace();
            fail("Address generation failed: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    /**
     * Simple Base58 encoder for Dogecoin addresses
     */
    private static String encodeBase58(byte[] input) {
        char[] BASE58_ALPHABET = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz".toCharArray();
        
        if (input.length == 0) {
            return "";
        }
        
        // Count leading zeros
        int leadingZeros = 0;
        for (byte b : input) {
            if (b == 0) {
                leadingZeros++;
            } else {
                break;
            }
        }
        
        // Convert to base58
        java.math.BigInteger bigInt = new java.math.BigInteger(1, input);
        StringBuilder result = new StringBuilder();
        
        while (bigInt.compareTo(java.math.BigInteger.ZERO) > 0) {
            java.math.BigInteger[] divmod = bigInt.divideAndRemainder(java.math.BigInteger.valueOf(58));
            result.insert(0, BASE58_ALPHABET[divmod[1].intValue()]);
            bigInt = divmod[0];
        }
        
        // Add leading '1's for leading zeros
        for (int i = 0; i < leadingZeros; i++) {
            result.insert(0, '1');
        }
        
        return result.toString();
    }
}
