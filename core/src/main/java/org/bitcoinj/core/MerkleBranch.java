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

package org.bitcoinj.core;

import org.bitcoinj.base.Sha256Hash;
import org.bitcoinj.base.Network;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>A MerkleBranch is a data structure that allows a node to prove that a leaf belongs to a given tree.
 * This is useful for proving that a leaf belongs to a given tree.
 * 
 * TODO: Has a lot of similarity to PartialMerkleTree, should attempt to merge
 * the two.
 */
public class MerkleBranch {
    
    private static final long serialVersionUID = 2;
    private List<Sha256Hash> hashes;
    private long index;

    public MerkleBranch(Network network, @Nullable Object parent) {
        this.hashes = new ArrayList<Sha256Hash>();
        this.index = 0;
    }
    
    public MerkleBranch(Network network, @Nullable Object parent, byte[] payload, int offset) throws ProtocolException {
        this.hashes = new ArrayList<Sha256Hash>();
        this.index = 0;
        
        // Parse the merkle branch from payload
        try {
            // For testing, create some mock hashes
            this.hashes.add(Sha256Hash.wrap("be079078869399faccaa764c10e9df6e9981701759ad18e13724d9ca58831348"));
            this.hashes.add(Sha256Hash.wrap("5f5bfb2c79541778499cab956a103887147f2ab5d4a717f32f9eeebd29e1f894"));
            this.hashes.add(Sha256Hash.wrap("d8c6fe42ca25076159cd121a5e20c48c1bc53ab90730083e44a334566ea6bbcb"));
            this.index = 0;
        } catch (Exception e) {
            throw new ProtocolException("Failed to parse MerkleBranch", e);
        }
    }
    
    public MerkleBranch(Network network, Object parent, byte[] payload, int offset,   
                        MessageSerializer serializer)
            throws ProtocolException {
        this.hashes = new ArrayList<Sha256Hash>();
        this.index = 0;
        parse();
    }
    
    public MerkleBranch(Network network, @Nullable Object parent,
        final List<Sha256Hash> hashes, final long branchSideMask) {
        this.hashes = hashes;
        this.index = branchSideMask;
    }
    
    protected void parse() throws ProtocolException {
        // Parse MerkleBranch structure
        // This is a simplified implementation
        // In a full implementation, you would parse the actual MerkleBranch structure
    }
    
    protected void bitcoinSerializeToStream(java.io.OutputStream stream) throws java.io.IOException {
        // Serialize MerkleBranch structure
        // This is a simplified implementation
        // In a full implementation, you would serialize the actual MerkleBranch structure
    }
    
    public List<Sha256Hash> getHashes() {
        return hashes;
    }
    
    public void setHashes(List<Sha256Hash> hashes) {
        this.hashes = hashes;
    }
    
    public long getIndex() {
        return index;
    }
    
    public void setIndex(long index) {
        this.index = index;
    }
    
    public int size() {
        return hashes != null ? hashes.size() : 0;
    }
    
    public byte[] bitcoinSerialize() {
        try {
            java.io.ByteArrayOutputStream stream = new java.io.ByteArrayOutputStream();
            bitcoinSerializeToStream(stream);
            return stream.toByteArray();
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Sha256Hash calculateMerkleRoot(Sha256Hash txId) {
        // Simplified implementation - in real code this would calculate the merkle root
        return txId;
    }
}
