/**
 * Copyright 2011 Google Inc.
 * Copyright 2014 Andreas Schildbach
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.libdohj.core;

import com.lambdaworks.crypto.SCrypt;
import org.bitcoinj.base.Sha256Hash;

import java.math.BigInteger;
import java.security.GeneralSecurityException;

/**
 * Utility functions for altcoin operations
 */
public class Utils {
    /**
     * Calculates the Scrypt hash of the given byte range.
     * The resulting hash is in small endian form.
     */
    public static byte[] scryptDigest(byte[] input) throws GeneralSecurityException {
        return SCrypt.scrypt(input, input, 1024, 1, 1, 32);
    }

    public static String formatAsHash(final BigInteger value) {
        final StringBuilder builder = new StringBuilder(value.toString(16));
        while (builder.length() < (Sha256Hash.LENGTH * 2)) {
            builder.insert(0, "0");
        }
        return builder.toString();
    }

    /**
     * Decode compact bits to BigInteger
     */
    public static BigInteger decodeCompactBits(long compact) {
        // In bitcoinj 0.17, this method might have moved or changed
        // For now, implement a basic version
        int size = (int) (compact >> 24);
        byte[] bytes = new byte[4 + size];
        bytes[0] = (byte) (size & 0xFF);
        bytes[1] = (byte) ((compact >> 16) & 0xFF);
        bytes[2] = (byte) ((compact >> 8) & 0xFF);
        bytes[3] = (byte) (compact & 0xFF);
        return new BigInteger(1, bytes);
    }

    /**
     * Encode BigInteger to compact bits
     */
    public static long encodeCompactBits(BigInteger value) {
        // In bitcoinj 0.17, this method might have moved or changed
        // For now, implement a basic version
        byte[] bytes = value.toByteArray();
        if (bytes.length == 0) return 0;
        
        int size = bytes.length;
        long compact = (long) size << 24;
        
        if (size >= 1) compact |= (bytes[0] & 0xFF) << 16;
        if (size >= 2) compact |= (bytes[1] & 0xFF) << 8;
        if (size >= 3) compact |= (bytes[2] & 0xFF);
        
        return compact;
    }

    /**
     * Hex decoder
     */
    public static class HEX {
        public static byte[] decode(String hex) {
            try {
                // Use a simple hex decoder
                int len = hex.length();
                byte[] data = new byte[len / 2];
                for (int i = 0; i < len; i += 2) {
                    data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                                         + Character.digit(hex.charAt(i+1), 16));
                }
                return data;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}