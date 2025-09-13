/**
 * Copyright 2013 Google Inc.
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

package com.dogecoin.dogecoinj.protocols.payments;

/**
 * <p>Utility methods and constants for working with <a href="https://github.com/bitcoin/bips/blob/master/bip-0070.mediawiki">
 * BIP 70 aka the payment protocol</a>. These are low level wrappers around the protocol buffers. If you're implementing
 * a wallet app, look at {@link PaymentSession} for a higher level API that should simplify working with the protocol.</p>
 *
 * <p>BIP 70 defines a binary, protobuf based protocol that runs directly between sender and receiver of funds. Payment
 * protocol data does not flow over the Bitcoin P2P network or enter the block chain. It's instead for data that is only
 * of interest to the parties involved but isn't otherwise needed for consensus.</p>
 * 
 * <p><strong>IMPORTANT:</strong> BIP70 payment protocol has been completely removed in bitcoinj 0.17 due to security 
 * concerns and limited adoption. This class is provided for backward compatibility only and all methods will throw 
 * UnsupportedOperationException.</p>
 * 
 * <p>For modern payment handling, consider using:</p>
 * <ul>
 * <li>BIP21 URIs for simple payment requests</li>
 * <li>PSBT (Partially Signed Bitcoin Transactions) for complex transactions</li>
 * <li>Direct transaction construction and signing</li>
 * </ul>
 */
public class PaymentProtocol {
    
    /**
     * @deprecated BIP70 has been removed in bitcoinj 0.17. Use BIP21 URIs or PSBT instead.
     */
    @Deprecated
    public static void deprecatedMethod() {
        throw new UnsupportedOperationException("BIP70 Payment Protocol has been removed in bitcoinj 0.17. " +
                "Please migrate to BIP21 URIs or PSBT for payment handling.");
    }
    
    /**
     * @deprecated BIP70 has been removed in bitcoinj 0.17. Use BIP21 URIs or PSBT instead.
     */
    @Deprecated
    public static void createPaymentRequest() {
        throw new UnsupportedOperationException("BIP70 Payment Protocol has been removed in bitcoinj 0.17. " +
                "Please migrate to BIP21 URIs or PSBT for payment handling.");
    }
    
    /**
     * @deprecated BIP70 has been removed in bitcoinj 0.17. Use BIP21 URIs or PSBT instead.
     */
    @Deprecated
    public static void parsePaymentRequest() {
        throw new UnsupportedOperationException("BIP70 Payment Protocol has been removed in bitcoinj 0.17. " +
                "Please migrate to BIP21 URIs or PSBT for payment handling.");
    }
}