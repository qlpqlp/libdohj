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
 * <p>Provides a standard implementation of the Payment Protocol (BIP 0070)</p>
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
public class PaymentSession {
    
    /**
     * @deprecated BIP70 has been removed in bitcoinj 0.17. Use BIP21 URIs or PSBT instead.
     */
    @Deprecated
    public PaymentSession() {
        throw new UnsupportedOperationException("BIP70 Payment Protocol has been removed in bitcoinj 0.17. " +
                "Please migrate to BIP21 URIs or PSBT for payment handling.");
    }
    
    /**
     * @deprecated BIP70 has been removed in bitcoinj 0.17. Use BIP21 URIs or PSBT instead.
     */
    @Deprecated
    public void sendPayment() {
        throw new UnsupportedOperationException("BIP70 Payment Protocol has been removed in bitcoinj 0.17. " +
                "Please migrate to BIP21 URIs or PSBT for payment handling.");
    }
    
    /**
     * @deprecated BIP70 has been removed in bitcoinj 0.17. Use BIP21 URIs or PSBT instead.
     */
    @Deprecated
    public void getPaymentRequest() {
        throw new UnsupportedOperationException("BIP70 Payment Protocol has been removed in bitcoinj 0.17. " +
                "Please migrate to BIP21 URIs or PSBT for payment handling.");
    }
}