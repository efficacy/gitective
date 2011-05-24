/******************************************************************************
 *  Copyright (c) 2011, Kevin Sawicki <kevinsawicki@gmail.com>
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *****************************************************************************/
package org.gitective.core.filter.commit;

import java.io.IOException;

import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.revwalk.filter.RevFilter;

/**
 * Filter that stops including commits after a certain number of commits are
 * considered. This class should be last in an {@link AndCommitFilter} if you
 * want to only limit matched commits and not just all visited commits.
 */
public class CommitLimitFilter extends CommitFilter {

	private long limit;
	private long count = 0L;

	/**
	 * Create a limit filter
	 * 
	 * @param limit
	 */
	public CommitLimitFilter(long limit) {
		this.limit = limit;
	}

	@Override
	public boolean include(RevWalk walker, RevCommit commit) throws IOException {
		return count++ < limit;
	}

	@Override
	public CommitFilter reset() {
		count = 0L;
		return super.reset();
	}

	@Override
	public RevFilter clone() {
		return new CommitLimitFilter(limit);
	}

}
