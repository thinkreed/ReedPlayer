package com.reed.reedplayer.utils

import com.reed.reedplayer.model.TreeNode

/**
 * Created by thinkreed on 2017/6/16.
 */

class Trees {
    fun traverse(root: TreeNode?) {
        if (root == null) return

        print(root.value)

        if (root.children != null) {
            for (node in root.children) {
                traverse(node)
            }
        }
    }
}
